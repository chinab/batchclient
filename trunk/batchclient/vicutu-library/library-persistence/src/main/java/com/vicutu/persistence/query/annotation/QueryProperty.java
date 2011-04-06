package com.vicutu.persistence.query.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vicutu.persistence.query.CriterionLikeMatchMode;
import com.vicutu.persistence.query.CriterionRestriction;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface QueryProperty {

	String target() default "";

	CriterionRestriction restriction() default CriterionRestriction.EQUAL;

	CriterionLikeMatchMode matchMode() default CriterionLikeMatchMode.ANYWHERE;
}
