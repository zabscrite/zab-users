package com.zab.zabusers.shared.common.domain;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception {

    private Long id;

    private Class resourceClass;


    public <T> ResourceNotFoundException(Class<T> clazz, Long id) {
        this.id = id;
        this.resourceClass = clazz;
    }

    public String getErrorMessage() {
        return String.format("%s resource not found.", resourceClass.getSimpleName());
    }
}
