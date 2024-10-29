package com.maua.backendMetro.util;

import com.maua.backendMetro.util.Impl.ExtinguisherDatesValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExtinguisherDatesValidatorImpl.class)
public @interface ValidExtinguisherDates {

    String message() default "Datas inválidas: Verifique se data da ultima recarga esta antes da data de expiração e da próxima inspeção, \nou se proxima inspeção está antes da data de expiração";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

