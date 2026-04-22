package com.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;

import com.loan.entity.Customer;
import com.loan.entity.LoanApplication;
import com.loan.dto.LoanResponse;
import com.loan.service.LoanService;

@RestController
public class CustomerController {

    @Autowired
    private LoanService loanService;

    // APPLY LOAN
    @PostMapping("/loan/apply")
    public LoanResponse applyLoan(@RequestBody Customer customer,
                                 @RequestParam double amount,
                                 @RequestParam int tenure) {

        return loanService.processLoan(customer, amount, tenure);
    }

    // GET LOAN BY ID
    @GetMapping("/loan/{id}")
    public String getLoan(@PathVariable Long id) {
        return "Loan details fetched for id: " + id;
    }

    // 🔥 PAGINATION API
    @GetMapping("/loan")
    public Page<LoanApplication> getLoans(
            @RequestParam String status,
            @RequestParam int page,
            @RequestParam int size) {

        return loanService.getLoans(status, page, size);
    }
}