package br.com.totvs.challenge.customermanager.core.validator;

public class CpfValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null) return false;

        cpf = removesTracesAndDots(cpf);

        if (isFormateInvalid(cpf)) return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int firstCheckDigit = 11 - (sum % 11);
        if (firstCheckDigit >= 10) firstCheckDigit = 0;

        if (firstCheckDigit != Character.getNumericValue(cpf.charAt(9))) return false;

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int secondCheckDigit = 11 - (sum % 11);
        if (secondCheckDigit >= 10) secondCheckDigit = 0;

        return secondCheckDigit == Character.getNumericValue(cpf.charAt(10));
    }

    private static String removesTracesAndDots(String cpf) {
        return cpf.replaceAll("[^\\d]", "");
    }

    private static boolean isFormateInvalid(String cpf) {
        return cpf.length() != 11 || cpf.matches("(\\d)\\1{10}");
    }
}
