#include <jni.h>
#include <dlfcn.h>
#include <string>
#include <cstdlib>
#include <cstring>

// C core exports (from downloaded libvless.so).
typedef char *(*StartCoreFn)(char *);
typedef void (*StopCoreFn)();

static void *g_libHandle = nullptr;
static StartCoreFn g_startCore = nullptr;
static StopCoreFn g_stopCore = nullptr;
static std::string g_lastPath;

static void throwRuntime(JNIEnv *env, const std::string &msg) {
    jclass exClass = env->FindClass("java/lang/RuntimeException");
    if (exClass) {
        env->ThrowNew(exClass, msg.c_str());
    }
}

extern "C" JNIEXPORT void JNICALL
Java_org_telegram_vless_VlessCoreBridge_nativeLoadCore(JNIEnv *env, jclass, jstring soPath_) {
    if (soPath_ == nullptr) {
        throwRuntime(env, "soPath is null");
        return;
    }

    const char *soPathChars = env->GetStringUTFChars(soPath_, nullptr);
    if (!soPathChars) {
        throwRuntime(env, "soPath UTF chars are null");
        return;
    }

    std::string soPath(soPathChars);
    env->ReleaseStringUTFChars(soPath_, soPathChars);

    if (soPath.empty()) {
        throwRuntime(env, "soPath is empty");
        return;
    }

    // Reload if path changed.
    if (g_libHandle && soPath == g_lastPath) {
        return;
    }

    if (g_libHandle) {
        dlclose(g_libHandle);
        g_libHandle = nullptr;
        g_startCore = nullptr;
        g_stopCore = nullptr;
    }

    g_libHandle = dlopen(soPath.c_str(), RTLD_NOW | RTLD_GLOBAL);
    if (!g_libHandle) {
        const char *err = dlerror();
        throwRuntime(env, std::string("dlopen failed: ") + (err ? err : "unknown"));
        return;
    }

    g_startCore = reinterpret_cast<StartCoreFn>(dlsym(g_libHandle, "StartCore"));
    g_stopCore = reinterpret_cast<StopCoreFn>(dlsym(g_libHandle, "StopCore"));

    if (!g_startCore || !g_stopCore) {
        throwRuntime(env, "Failed to find StartCore/StopCore symbols in libvless");
        return;
    }

    g_lastPath = soPath;
}

extern "C" JNIEXPORT jstring JNICALL
Java_org_telegram_vless_VlessCoreBridge_nativeStartCore(JNIEnv *env, jclass, jstring configJson_) {
    if (!g_libHandle || !g_startCore) {
        throwRuntime(env, "Core library is not loaded. Call nativeLoadCore first.");
        return env->NewStringUTF("");
    }

    if (configJson_ == nullptr) {
        throwRuntime(env, "configJson is null");
        return env->NewStringUTF("");
    }

    const char *configChars = env->GetStringUTFChars(configJson_, nullptr);
    if (!configChars) {
        throwRuntime(env, "configJson UTF chars are null");
        return env->NewStringUTF("");
    }

    // Core expects char* (non-const). Provide an owned buffer.
    std::string configStr(configChars);
    env->ReleaseStringUTFChars(configJson_, configChars);

    char *buf = (char *)std::malloc(configStr.size() + 1);
    if (!buf) {
        throwRuntime(env, "malloc failed");
        return env->NewStringUTF("");
    }
    std::memcpy(buf, configStr.c_str(), configStr.size());
    buf[configStr.size()] = '\0';

    char *res = g_startCore(buf);

    std::free(buf);

    if (!res) {
        // Success often returns empty string.
        return env->NewStringUTF("");
    }

    std::string resStr(res);
    // The Python example frees using libc.free, so free here as well.
    std::free(res);

    return env->NewStringUTF(resStr.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_org_telegram_vless_VlessCoreBridge_nativeStopCore(JNIEnv *env, jclass clazz) {
    (void)env;
    (void)clazz;

    if (g_stopCore) {
        try {
            g_stopCore();
        } catch (...) {
            // Swallow: we don't want JNI to crash on stop.
        }
    }
}

