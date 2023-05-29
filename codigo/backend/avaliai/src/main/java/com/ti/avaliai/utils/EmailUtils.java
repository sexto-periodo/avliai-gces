package com.ti.avaliai.utils;

import java.util.List;



public class EmailUtils {
    private static final List<String> ACCEPTED_DOIMAINS = List.of(new String[]{
            "sga.pucminas.br"
    });

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || atIndex == email.length() - 1) {
            return false;
        }

        String domain = email.substring(atIndex + 1);
        return ACCEPTED_DOIMAINS.contains(domain);
    }
}
