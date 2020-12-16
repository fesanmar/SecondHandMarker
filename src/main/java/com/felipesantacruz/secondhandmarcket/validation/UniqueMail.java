package com.felipesantacruz.secondhandmarcket.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueMailValidator.class)
@Retention(RUNTIME)
@Target(FIELD)
public @interface UniqueMail
{
	public String message() default "There is already user with this username!";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};
}
