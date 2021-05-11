package br.com.zupacademy.caio.proposta.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class MyException {

    MessageSource messageSource;

    MyException(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException exception) {
        var result = exception.getFieldErrors()
                .stream()
                .map(e -> new ExceptionResponse(e.getField(), messageSource.getMessage(e, Locale.getDefault())))
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(result);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handle(NotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<?> handle(MissingPathVariableException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handle(ResponseStatusException exception) {
        return ResponseEntity.unprocessableEntity()
                .body(List.of(new ExceptionResponse("documento", "valor informado já existe")));
    }
}
