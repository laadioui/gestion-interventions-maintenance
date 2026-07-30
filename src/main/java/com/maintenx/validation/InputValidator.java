package com.maintenx.validation;

import com.maintenx.exception.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public final class InputValidator {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE = Pattern.compile("^[0-9 +().-]{6,20}$");
    private InputValidator() {}
    public static void required(String value, String champ) {
        if (value == null || value.trim().isEmpty()) throw new ValidationException("Le champ " + champ + " est obligatoire.");
    }
    public static void email(String value) { required(value, "email"); if (!EMAIL.matcher(value).matches()) throw new ValidationException("Adresse email invalide."); }
    public static void phone(String value) { if (value != null && !value.isBlank() && !PHONE.matcher(value).matches()) throw new ValidationException("Numéro de téléphone invalide."); }
    public static void password(String value) { required(value, "mot de passe"); if (value.length() < 8) throw new ValidationException("Le mot de passe doit contenir au moins huit caractères."); }
    public static void nonNegative(BigDecimal value, String champ) { if (value != null && value.signum() < 0) throw new ValidationException("Le champ " + champ + " ne peut pas être négatif."); }
    public static void chronological(LocalDateTime start, LocalDateTime end) { if (start != null && end != null && end.isBefore(start)) throw new ValidationException("La date de fin ne peut pas être antérieure à la date de début."); }
}
