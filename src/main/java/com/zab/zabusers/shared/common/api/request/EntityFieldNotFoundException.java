package com.zab.zabusers.shared.common.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityFieldNotFoundException extends Exception {

    private Class entityClass;

    private String fieldName;

    private Long id;

    public EntityFieldNotFoundException(Class entityClass, String fieldName, Long id) {
        super(String.format("%s %s not found for %s", entityClass.getSimpleName(), id.toString(), fieldName));
        this.entityClass = entityClass;
        this.fieldName = fieldName;
        this.id = id;

    }
}
