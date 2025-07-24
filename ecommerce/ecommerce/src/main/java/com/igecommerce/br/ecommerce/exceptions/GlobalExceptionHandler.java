package com.igecommerce.br.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Tratando CampoInvalidoException
    @ExceptionHandler(CampoInvalidoException.class)
    public ResponseEntity<Map<String, Object>> handleCampoInvalidoException(CampoInvalidoException ex) {
        Map<String, Object> response = Map.of(
                "status", 422,
                "message", "Erro de Validação",
                "errors", List.of(Map.of("field", ex.getCampo(), "error", ex.getMessage()))
        );

        return ResponseEntity.unprocessableEntity().body(response);


    }
    //Tratando a exceção de validação automática do Spring
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = ex.getBindingResult().getFieldErrors().stream().collect((Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
        Map<String, Object> response = Map.of("status", 400,"message", "Erro validação", "errors",errors);
        return ResponseEntity.badRequest().body(response);
    }

    //Tratando RegistroDuplicadoExecption

    @ExceptionHandler(RegistroDuplicadoException.class)
    public ResponseEntity<Map<String, Object>> handleRegistroDuplicadoException(RegistroDuplicadoException ex) {
        Map<String, Object> response = Map.of("status", 409, "message", "RegistroDuplicado","errors", List.of());

       return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
