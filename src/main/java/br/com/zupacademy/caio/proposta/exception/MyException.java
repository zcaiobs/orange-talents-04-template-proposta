package br.com.zupacademy.caio.proposta.exception;

import br.com.zupacademy.caio.proposta.nova_proposta.FeignResponse;
import com.mysql.cj.xdevapi.JsonParser;
import feign.FeignException;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.nio.ByteBuffer;
import java.util.Arrays;
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

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> teste(ResponseStatusException exception) {
        return ResponseEntity.unprocessableEntity()
                .body(List.of(new ExceptionResponse("documento", "valor informado já existe")));
    }

    //@ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<?> teste(HttpClientErrorException exception) {
        var result = exception;
        return ResponseEntity.unprocessableEntity().body(result);
    }
}
