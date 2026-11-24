package ru.edu.pr08;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    private static final int MAX_NAME_LENGTH = 15;

    public List<String> validate(ClientDraft d) {
        List<String> errors = new ArrayList<>();
        if (d == null) {
            errors.add("Draft is null");
            return errors;
        }

        if (d.inn() == null || !d.inn().matches("\\d{10}|\\d{12}")) {
            errors.add("INN must contain 10 or 12 digits");
        }

        if (d.name() == null || d.name().isBlank()) {
            errors.add("Name must not be blank");
        } else if (d.name().length() > MAX_NAME_LENGTH) {
            errors.add("Name length must not exceed " + MAX_NAME_LENGTH + " characters");
        }

        if (d.email() == null || !isValidEmail(d.email())) {
            errors.add("Invalid email format");
        }

        return errors;
    }

    private boolean isValidEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex <= 0) {
            return false;
        }
        if (email.indexOf('@', atIndex + 1) != -1) {
            return false;
        }
        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex + 1);
        return !localPart.isBlank() && !domainPart.isBlank();
    }
}