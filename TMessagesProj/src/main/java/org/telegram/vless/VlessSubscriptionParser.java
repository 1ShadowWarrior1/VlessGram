package org.telegram.vless;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class VlessSubscriptionParser {
    private VlessSubscriptionParser() {
    }

    public static final class NodeSeed {
        public final String uri;
        public final String name;

        public NodeSeed(String uri, String name) {
            this.uri = uri;
            this.name = name;
        }
    }

    public static List<NodeSeed> extractNodesFromSubscriptionText(String raw) {
        List<NodeSeed> out = new ArrayList<>();
        if (raw == null) {
            return out;
        }
        String trimmed = raw.trim();
        if (trimmed.isEmpty()) {
            return out;
        }

        String low = trimmed.toLowerCase(Locale.ROOT);
        boolean looksLikeUri = low.contains("vless://") || low.contains("trojan://") || low.contains("ss://");

        // 1) JSON-first parsing
        if (looksLikeJson(trimmed)) {
            try {
                Object parsed = new JSONTokener(trimmed).nextValue();
                Map<String, String> uriToNameOverride = new LinkedHashMap<>();
                walk(parsed, uriToNameOverride, null);

                for (Map.Entry<String, String> e : uriToNameOverride.entrySet()) {
                    VlessConfigParser.ParsedConfig pc = VlessConfigParser.parseToLibConfigJson(e.getKey(), 1080);
                    if (pc != null) {
                        String name = e.getValue();
                        if (name == null || name.trim().isEmpty()) {
                            name = pc.name;
                        }
                        out.add(new NodeSeed(e.getKey(), name));
                    }
                }
                return out;
            } catch (Exception ignored) {
                // fallback to classic parsing
            }
        }

        // 2) Classic/base64 parsing (same idea as Python example)
        String candidate = trimmed;
        if (!looksLikeUri) {
            // Might be base64-wrapped JSON/plain text.
            try {
                String b64 = candidate;
                int missingPadding = b64.length() % 4;
                if (missingPadding != 0) {
                    int pads = 4 - missingPadding;
                    StringBuilder sb = new StringBuilder(b64);
                    for (int i = 0; i < pads; i++) {
                        sb.append('=');
                    }
                    b64 = sb.toString();
                }
                byte[] decoded = Base64.getDecoder().decode(b64);
                String decodedText = new String(decoded, StandardCharsets.UTF_8).trim();
                if (!decodedText.isEmpty()) {
                    if (looksLikeJson(decodedText)) {
                        List<NodeSeed> fromJson = extractNodesFromSubscriptionText(decodedText);
                        if (!fromJson.isEmpty()) {
                            return fromJson;
                        }
                    }
                    candidate = decodedText;
                }
            } catch (Exception ignored) {
            }
        }

        // Split by lines and parse entries.
        String[] lines = candidate.split("\\r?\\n");
        for (String line : lines) {
            String l = line.trim();
            if (l.isEmpty()) continue;
            String ll = l.toLowerCase(Locale.ROOT);
            if (ll.startsWith("vless://") || ll.startsWith("trojan://") || ll.startsWith("ss://")) {
                VlessConfigParser.ParsedConfig pc = VlessConfigParser.parseToLibConfigJson(l, 1080);
                if (pc != null) {
                    out.add(new NodeSeed(l, pc.name));
                }
            }
        }
        return out;
    }

    private static boolean looksLikeJson(String s) {
        if (s == null) return false;
        String t = s.trim();
        return (t.startsWith("{") && t.endsWith("}")) || (t.startsWith("[") && t.endsWith("]"));
    }

    private static boolean isUriString(String s) {
        if (s == null) return false;
        String low = s.trim().toLowerCase(Locale.ROOT);
        return low.startsWith("vless://") || low.startsWith("trojan://") || low.startsWith("ss://");
    }

    /**
     * Recursively walks JSON and collects URIs.
     * If the current object has common name fields, we treat that as an override
     * for any URI strings found within the same subtree.
     */
    private static void walk(Object obj, Map<String, String> uriToNameOverride, String nameOverride) {
        if (obj == null) {
            return;
        }
        if (obj instanceof String) {
            String s = ((String) obj).trim();
            if (isUriString(s)) {
                if (!uriToNameOverride.containsKey(s)) {
                    uriToNameOverride.put(s, nameOverride);
                }
            }
            return;
        }

        if (obj instanceof JSONObject) {
            JSONObject jo = (JSONObject) obj;
            String nextOverride = nameOverride;
            String[] nameKeys = new String[]{"name", "title", "remark", "ps", "comment"};
            for (String k : nameKeys) {
                String v = jo.optString(k, null);
                if (v != null && !v.trim().isEmpty()) {
                    nextOverride = v.trim();
                    break;
                }
            }
            // Scan values recursively.
            for (Iterator<String> it = jo.keys(); it.hasNext(); ) {
                String key = it.next();
                walk(jo.opt(key), uriToNameOverride, nextOverride);
            }
            return;
        }

        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            for (int i = 0; i < arr.length(); i++) {
                walk(arr.opt(i), uriToNameOverride, nameOverride);
            }
        }
    }
}

