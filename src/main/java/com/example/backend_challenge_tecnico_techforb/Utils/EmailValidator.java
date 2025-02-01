package com.example.backend_challenge_tecnico_techforb.Utils;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";

    public static boolean esEmailValido(String email){
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
}
