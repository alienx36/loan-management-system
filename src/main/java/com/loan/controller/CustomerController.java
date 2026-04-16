package com.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.loan.dto.LoanRequest;
import com.loan.dto.LoanResponse;
import com.loan.entity.Customer;
import com.loan.entity.LoanApplication;
import com.loan.repository.LoanApplicationRepository;
import com.loan.service.LoanService;

import java.util.Optional;

@RestController
@RequestMapping("/loan")
public class CustomerController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    // APPLY LOAN
    @PostMapping("/apply")
    public LoanResponse applyLoan(@RequestBody LoanRequest request) {

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setIncome(request.getIncome());
        customer.setCreditScore(request.getCreditScore());

        return loanService.processLoan(
                customer,
                request.getAmount(),
                request.getTenure()
        );
    }

    // FETCH LOAN BY ID
    @GetMapping("/{id}")
    public LoanApplication getLoan(@PathVariable Long id) {

        Optional<LoanApplication> loan = loanApplicationRepository.findById(id);

        if (loan.isEmpty()) {
            throw new RuntimeException("Loan not found");
        }

        return loan.get();
    }
}