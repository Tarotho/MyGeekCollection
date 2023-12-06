package com.mygeekcollection.backend.exceptionhandler;

import com.mygeekcollection.backend.exceptionhandler.exceptions.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static com.mygeekcollection.backend.exceptionhandler.ErrorConstants.MESSAGE_BAD_REQUEST;
import static com.mygeekcollection.backend.exceptionhandler.ErrorConstants.MESSAGE_CONFLICT;
import static com.mygeekcollection.backend.exceptionhandler.ErrorConstants.MESSAGE_ENTITY_NOT_FOUND;
import static com.mygeekcollection.backend.exceptionhandler.ErrorConstants.MESSAGE_INTERNAL_SERVER_ERROR;
import static com.mygeekcollection.backend.exceptionhandler.ErrorConstants.MESSAGE_INVALID_VALUE;
import static com.mygeekcollection.backend.exceptionhandler.ErrorConstants.MESSAGE_UNAUTHORIZED;
import static com.mygeekcollection.backend.exceptionhandler.ErrorConstants.MESSAGE_USER_NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Manejo de EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(MESSAGE_ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    // Manejo de IllegalArgumentException con validación de entrada
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Logueo del error
        // Log.error("Bad Request: " + ex.getMessage());
        return new ResponseEntity<>(MESSAGE_BAD_REQUEST + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Manejo de UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(MESSAGE_USER_NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    // Manejo de JwtException relacionado con seguridad
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleJwtException(JwtException ex) {
        // Logueo del error sin revelar detalles sensibles
        // Log.error("JWT Exception: " + ex.getMessage());
        return new ResponseEntity<>(MESSAGE_UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    // Manejo de DataIntegrityViolationException con información específica
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = MESSAGE_CONFLICT;
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    // Manejo genérico de otras excepciones
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Logueo del error
        // Log.error("Internal Server Error: " + ex.getMessage());
        return new ResponseEntity<>(MESSAGE_INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        // Mapear los errores de validación a un mapa
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Manejo de ConstraintViolationException para validación de patrón u otras validaciones
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = String.format(MESSAGE_INVALID_VALUE, fieldName);
            errors.put(fieldName, errorMessage);
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}

