package com.zab.zabusers.subscription.api.response;

import com.zab.zabusers.subscription.domain.entity.Customer;
import lombok.Getter;

@Getter
public class CustomerResponse {

    private Long id;

    private String name;

    public CustomerResponse(Customer customer) {
        id = customer.getId();
        name = customer.getName();
    }
}
