package com.zab.zabusers.shared.common.domain;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception {

    private Long id;


    public ResourceNotFoundException(Long id) {
        this.id = id;
    }
}
