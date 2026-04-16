package com.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.loan.entity.LoanAccount;

public interface LoanAccountRepository extends JpaRepository<LoanAccount, Long> {
}