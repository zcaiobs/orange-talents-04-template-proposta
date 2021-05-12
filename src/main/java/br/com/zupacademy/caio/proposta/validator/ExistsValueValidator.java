package br.com.zupacademy.caio.proposta.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Object> {

    String domainAttribute;
    Class<?> klass;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(ExistsValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return entityManager
                .createQuery("select c from "+klass.getName()+" c where "+domainAttribute+" = :value")
                .setParameter("value", value)
                .getResultList().isEmpty();
    }
}
