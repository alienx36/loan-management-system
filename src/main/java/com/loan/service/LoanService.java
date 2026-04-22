package com.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.loan.entity.*;
import com.loan.repository.*;
import com.loan.dto.LoanResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanEligibilityRepository loanEligibilityRepository;

    @Autowired
    private LoanAccountRepository loanAccountRepository;

    @Autowired
    private EmiScheduleRepository emiScheduleRepository;

    public LoanResponse processLoan(Customer customer, double amount, int tenure) {

        logger.info("Starting loan processing for: {}", customer.getEmail());

        if (customer.getIncome() <= 0) {
            logger.error("Invalid income");
            throw new RuntimeException("Income must be greater than 0");
        }

        if (customer.getCreditScore() < 300 || customer.getCreditScore() > 900) {
            logger.error("Invalid credit score");
            throw new RuntimeException("Credit score must be between 300 and 900");
        }

        if (amount <= 0) {
            logger.error("Invalid loan amount");
            throw new RuntimeException("Loan amount must be greater than 0");
        }

        if (tenure <= 0) {
            logger.error("Invalid tenure");
            throw new RuntimeException("Tenure must be greater than 0");
        }

        customer = customerRepository.save(customer);

        LoanApplication application = new LoanApplication();
        application.setAmount(amount);
        application.setTenure(tenure);
        application.setStatus("PENDING");
        application.setCustomer(customer);

        application = loanApplicationRepository.save(application);

        boolean eligible = checkEligibility(customer, amount);

        LoanEligibility eligibility = new LoanEligibility();
        eligibility.setEligible(eligible);
        eligibility.setLoanApplication(application);

        if (!eligible) {
            logger.warn("Loan rejected for customer: {}", customer.getEmail());

            eligibility.setReason("Failed eligibility criteria");
            application.setStatus("REJECTED");

            loanEligibilityRepository.save(eligibility);
            loanApplicationRepository.save(application);

            return new LoanResponse("REJECTED", 0, "Loan Rejected");
        }

        eligibility.setReason("Eligible");
        loanEligibilityRepository.save(eligibility);

        double emi = calculateEmi(amount, tenure);
        double totalAmount = emi * tenure;

        LoanAccount account = new LoanAccount();
        account.setEmi(emi);
        account.setTotalAmount(totalAmount);
        account.setLoanApplication(application);

        account = loanAccountRepository.save(account);

        List<EmiSchedule> list = new ArrayList<>();

        for (int i = 1; i <= tenure; i++) {
            EmiSchedule e = new EmiSchedule();
            e.setMonth(i);
            e.setEmiAmount(emi);
            e.setLoanAccount(account);
            list.add(e);
        }

        emiScheduleRepository.saveAll(list);

        application.setStatus("APPROVED");
        loanApplicationRepository.save(application);

        logger.info("Loan approved. EMI: {}", emi);

        return new LoanResponse("APPROVED", emi, "Loan processed successfully");
    }

    public boolean checkEligibility(Customer customer, double amount) {

        if (customer.getIncome() < 25000)
            return false;

        if (customer.getCreditScore() < 650)
            return false;

        if (amount > customer.getIncome() * 10)
            return false;

        return true;
    }

    public double calculateEmi(double principal, int tenure) {

        double rate = 0.1 / 12;

        return (principal * rate * Math.pow(1 + rate, tenure)) /
               (Math.pow(1 + rate, tenure) - 1);
    }

    // 🔥 PAGINATION METHOD
    public Page<LoanApplication> getLoans(String status, int page, int size) {
        return loanApplicationRepository.findByStatus(status, PageRequest.of(page, size));
    }
}