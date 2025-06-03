package br.com.totvs.challenge.customermanager.customer.domain.exception;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String mensage) {
        super(mensage);
    }
}
