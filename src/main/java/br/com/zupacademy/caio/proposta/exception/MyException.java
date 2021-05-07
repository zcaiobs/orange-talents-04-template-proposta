package br.com.zupacademy.caio.proposta.exception;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
}
