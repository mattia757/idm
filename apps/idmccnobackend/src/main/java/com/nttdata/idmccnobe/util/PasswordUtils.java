package com.nttdata.idmccnobe.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtils {

    private PasswordUtils() {
    }

    public static String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public static boolean matches(String rawPassword, String storedPassword) {
        if (rawPassword == null || storedPassword == null || storedPassword.trim().isEmpty()) {
            return false;
        }
        if (isBcryptHash(storedPassword)) {
            return BCrypt.checkpw(rawPassword, storedPassword);
        }
        return storedPassword.equalsIgnoreCase(rawPassword);
    }

    public static boolean needsRehash(String storedPassword) {
        return !isBcryptHash(storedPassword);
    }

    private static boolean isBcryptHash(String storedPassword) {
        return storedPassword.startsWith("$2a$")
                || storedPassword.startsWith("$2b$")
                || storedPassword.startsWith("$2y$");
    }
}
