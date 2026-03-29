package org.telegram.vless;

import java.util.Locale;

/**
 * Minimal URI parser helpers for libvless nodes.
 */
public final class VlessUriUtils {
    private VlessUriUtils() {
    }

    public static final class HostPort {
        public final String host;
        public final int port;

        public HostPort(String host, int port) {
            this.host = host;
            this.port = port;
        }
    }

    /**
     * Extracts remote host/port from {@code vless://}/{@code trojan://}/{@code ss://} URIs.
     * Used for ping/availability checks (TCP connect).
     */
    public static HostPort extractHostPort(String uri) {
        if (uri == null) {
            return null;
        }
        String s = uri.trim();
        if (s.isEmpty()) {
            return null;
        }
        String low = s.toLowerCase(Locale.ROOT);

        String noFrag = s;
        int hash = s.indexOf('#');
        if (hash >= 0) {
            noFrag = s.substring(0, hash);
        }

        try {
            if (low.startsWith("vless://")) {
                String part = noFrag.substring("vless://".length());
                int at = part.indexOf('@');
                if (at < 0) return null;
                String rest = part.substring(at + 1);
                // Strip query params.
                int q = rest.indexOf('?');
                String hostPort = q >= 0 ? rest.substring(0, q) : rest;
                return parseHostPort(hostPort);
            } else if (low.startsWith("trojan://")) {
                String part = noFrag.substring("trojan://".length());
                int at = part.indexOf('@');
                if (at < 0) return null;
                String rest = part.substring(at + 1);
                int q = rest.indexOf('?');
                String hostPort = q >= 0 ? rest.substring(0, q) : rest;
                return parseHostPort(hostPort);
            } else if (low.startsWith("ss://")) {
                String part = noFrag.substring("ss://".length());
                int q = part.indexOf('?');
                String base = q >= 0 ? part.substring(0, q) : part;
                int at = base.lastIndexOf('@');
                if (at < 0) return null;
                String hostPort = base.substring(at + 1);
                return parseHostPort(hostPort);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    private static HostPort parseHostPort(String hostPort) {
        if (hostPort == null) return null;
        String hp = hostPort.trim();
        if (hp.isEmpty()) return null;
        try {
            if (hp.startsWith("[")) {
                int end = hp.indexOf(']');
                if (end < 0) return null;
                if (hp.length() <= end + 2 || hp.charAt(end + 1) != ':') return null;
                String host = hp.substring(1, end);
                String portStr = hp.substring(end + 2);
                int port = Integer.parseInt(portStr);
                return new HostPort(host, port);
            } else {
                int colon = hp.lastIndexOf(':');
                if (colon < 0) return null;
                String host = hp.substring(0, colon);
                String portStr = hp.substring(colon + 1);
                int port = Integer.parseInt(portStr);
                return new HostPort(host, port);
            }
        } catch (Exception e) {
            return null;
        }
    }
}

