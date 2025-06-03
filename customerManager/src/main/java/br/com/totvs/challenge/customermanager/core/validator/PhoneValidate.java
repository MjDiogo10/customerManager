package br.com.totvs.challenge.customermanager.core.validator;

public class PhoneValidate {

    public static boolean isValid(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^[0-9]{13}$");
    }
}
