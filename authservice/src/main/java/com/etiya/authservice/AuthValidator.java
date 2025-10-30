package com.etiya.authservice;

import com.etiya.common.crosscuttingconcerns.exceptions.types.AuthException;
import java.util.regex.Pattern;

public final class AuthValidator {

    private AuthValidator() {}

    // Regex constants
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static final String STRONG_PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-\\=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern STRONG_PASSWORD_PATTERN = Pattern.compile(STRONG_PASSWORD_REGEX);

    public static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new AuthException("Email cannot be empty");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new AuthException("Invalid email format");
        }
    }

    public static void validatePasswordStrong(String password) {
        if (password == null || password.isBlank()) {
            throw new AuthException("Password cannot be empty");
        }
        if (!STRONG_PASSWORD_PATTERN.matcher(password).matches()) {
            throw new AuthException("Password must be at least 8 characters and include uppercase, lowercase, digit and special character");
        }
    }
}
