package br.com.zupacademy.caio.proposta.validator;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    @PersistenceContext
    EntityManager entityManager;

    Class<?> domain;
    String field;

    @Override
    public void initialize(ValorUnico params) {
        domain = params.domain();
        field = params.field();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        var result = entityManager.createQuery("select u from "+domain.getName()+" u where "+field+" = :value")
                .setParameter("value", value)
                .getResultList()
                .isEmpty();
        if (!result) throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        return true;
    }
}
