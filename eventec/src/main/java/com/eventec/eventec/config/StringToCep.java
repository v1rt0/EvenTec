package com.eventec.eventec.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToCep {

    public static String extractInstitutionName(String text) {
        Pattern pattern = Pattern.compile("^.*(?=- Endereço:)");
        Matcher matcher = pattern.matcher(text.trim());
        if (matcher.find()) {
            return matcher.group().trim();
        }
        return null;
    }

    public static String extractAddress(String text) {
        Pattern pattern = Pattern.compile("Endereço: (.*?)(?=, \\d{5}-\\d{3})");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }

    public static String extractCep(String text) {
        Pattern pattern = Pattern.compile("\\d{5}-\\d{3}");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public static void main(String[] args) {
        String text = "Fatec São Bernardo do Campo – Adib Moises Dib - Endereço: Av. Pereira Barreto, 400 - Vila Baeta Neves - Centro, São Bernardo do Campo - SP, 09751-000";

        System.out.println("Institution Name: " + extractInstitutionName(text));
        System.out.println("Address: " + extractAddress(text));
        System.out.println("CEP: " + extractCep(text));
    }
}