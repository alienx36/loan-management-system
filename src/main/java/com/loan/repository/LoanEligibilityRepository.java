package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loan.entity.LoanEligibility;

public interface LoanEligibilityRepository extends JpaRepository<LoanEligibility, Long> {
}