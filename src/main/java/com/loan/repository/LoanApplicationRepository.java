package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loan.entity.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}