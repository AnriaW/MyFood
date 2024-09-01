package br.ufal.myfood.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {

    private static final Locale LOCALE = new Locale("pt", "BR");

    public static String formatCurrency(double value) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(LOCALE);
        return currencyFormat.format(value);
    }

    public static String formatName(String name) {
        if (name == null) {
            return "";
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public static String formatEmail(String email) {
        return email.toLowerCase();
    }
}
