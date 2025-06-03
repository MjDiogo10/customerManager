package br.com.totvs.challenge.customermanager.core.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CpfValidatorTest {

    @Test
    void shouldReturnFalseWhenCpfIsNull() {
        assertFalse(CpfValidator.isValid(null));
    }

    @Test
    void shouldReturnFalseWhenCpfHasIncorrectLength() {
        assertFalse(CpfValidator.isValid("123456789"));
        assertFalse(CpfValidator.isValid("123456789012"));
    }

    @Test
    void shouldReturnFalseWhenCpfHasAllDigitsEquals() {
        assertFalse(CpfValidator.isValid("00000000000"));
    }

    @Test
    void shouldReturnTrueForValidCpfWithoutFormatting() {
        assertTrue(CpfValidator.isValid("52998224725"));
    }

    @Test
    void shouldReturnTrueForValidCpfWithFormatting() {
        assertTrue(CpfValidator.isValid("529.982.247-25"));
        assertTrue(CpfValidator.isValid("100.000.002-80"));
    }

    @Test
    void shouldReturnFalseForInvalidCpf() {
        assertFalse(CpfValidator.isValid("52998224724"));
        assertFalse(CpfValidator.isValid("abc998224724"));
        assertFalse(CpfValidator.isValid("123.456.789-00"));
        assertFalse(CpfValidator.isValid("111.222.333-44"));
    }

    @Test
    void shouldReturnFalseForCpfWithInvalidCharacters() {
        assertFalse(CpfValidator.isValid("52998a24725"));
        assertFalse(CpfValidator.isValid("52998@24725"));
    }
}
