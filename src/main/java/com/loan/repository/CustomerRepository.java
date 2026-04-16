package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loan.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}