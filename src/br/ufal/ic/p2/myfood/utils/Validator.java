package br.ufal.myfood.utils;

public class Validator {

    public static boolean isValidString(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean isPositiveDouble(double value) {
        return value > 0;
    }

    public static boolean isValidCategory(String category) {
        return isValidString(category);
    }

    public static boolean isValidName(String name) {
        return isValidString(name);
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean isValidCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }
}
