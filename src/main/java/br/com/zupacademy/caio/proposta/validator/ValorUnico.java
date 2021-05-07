package br.com.zupacademy.caio.proposta.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ValorUnicoValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValorUnico {

    String message() default "valor informado já existe";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domain();

    String field();
}
