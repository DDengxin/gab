package com.tengzhi.base.jpa.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Embeddable;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.Set;

/**
 * @author  lgl
 *
 */
public class BaseModel implements Serializable {

    /**
     * 验证当前实体类
     *
     * @param <T>
     */
    public <T> void Validate() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<T>> set = validator.validate((T) this);

        for (ConstraintViolation<T> constraintViolation : set) {
            throw new IllegalArgumentException(constraintViolation.getMessage());
        }
    }

    /**
     * 反射获取实体类字段属性
     * @return
     */
    @Override
    public String toString() {
        try {
            return ReflectionToStringBuilder.toString(this);
        } catch (Exception e) {
            return super.toString();
        }

    }
}

