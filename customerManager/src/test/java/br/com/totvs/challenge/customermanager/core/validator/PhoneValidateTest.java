package br.com.totvs.challenge.customermanager.core.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PhoneValidateTest {

    @Test
    void shouldReturnFalseWhenPhoneIsNull() {
        assertFalse(PhoneValidate.isValid(null));
    }

    @Test
    void shouldReturnFalseWhenPhoneDoesNotHave13Digits() {
        assertFalse(PhoneValidate.isValid("123456789012"));
        assertFalse(PhoneValidate.isValid("12345678901234"));
    }

    @Test
    void shouldReturnFalseWhenPhoneHasNonNumericCharacters() {
        assertFalse(PhoneValidate.isValid("12345678901a3"));
        assertFalse(PhoneValidate.isValid("12345678901-3"));
        assertFalse(PhoneValidate.isValid("123 456789012"));
    }

    @Test
    void shouldReturnTrueForValidPhone() {
        assertTrue(PhoneValidate.isValid("5562991327958"));
    }
}
