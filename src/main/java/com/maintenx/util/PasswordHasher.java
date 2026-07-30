package com.maintenx.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordHasher {
    private PasswordHasher() {}
    public static String hash(String raw) { return BCrypt.hashpw(raw, BCrypt.gensalt(12)); }
    public static boolean verify(String raw, String hash) { return raw != null && hash != null && BCrypt.checkpw(raw, hash); }
}
