package br.com.totvs.challenge.customermanager.core.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.totvs.challenge.customermanager.customer.domain.exception.BusinessRuleException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<String> handleRegraNegocioException(BusinessRuleException ex) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(ex.getMessage());
  }
}
