# Loan Management System

## Overview
A backend system that processes loan applications with eligibility checks, EMI calculation, and full lifecycle management.

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger (OpenAPI)

## Features
- Apply for loan via REST API
- Eligibility check based on income and credit score
- EMI calculation
- Stores data across multiple tables
- Fetch loan details by ID
- Input validation and global exception handling
- API documentation using Swagger
- Logging using SLF4J

## API Endpoints
- POST /loan/apply
- GET /loan/{id}

## Database Tables
- customer
- loan_application
- loan_eligibility
- loan_account
- emi_schedule

## How to Run
1. Configure PostgreSQL
2. Run Spring Boot application
3. Open Swagger UI: http://localhost:8081/swagger-ui.html
