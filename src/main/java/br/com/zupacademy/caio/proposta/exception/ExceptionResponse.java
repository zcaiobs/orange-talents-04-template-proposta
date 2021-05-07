package br.com.zupacademy.caio.proposta.exception;

public class ExceptionResponse {
    String field;
    String message;

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
