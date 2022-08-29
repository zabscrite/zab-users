package com.zab.zabusers.subscription.domain.repository;

import com.zab.zabusers.subscription.domain.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
