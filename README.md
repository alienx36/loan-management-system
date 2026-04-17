# Loan Management System

## 📌 Overview
A backend system that processes loan applications with eligibility checks, EMI calculation, and full lifecycle management.  
It simulates a basic loan module of a core banking system.

---

## ⚙️ Tech Stack
- Java 17  
- Spring Boot  
- Spring Data JPA (Hibernate)  
- PostgreSQL  
- Swagger (OpenAPI)  
- Postman (API testing)  

---

## 🚀 Features

### Loan Processing Workflow
- Customer applies for a loan via API  
- System checks eligibility:
  - Income ≥ 25,000  
  - Credit Score ≥ 650  
  - Loan amount ≤ 10× income  
- EMI is calculated using standard formula  
- Loan is APPROVED or REJECTED  
- Data stored across multiple related tables  

---

## 📡 API Endpoints

### POST /loan/apply  
Apply for loan

### GET /loan/{id}  
Fetch loan details

---

## 🗄️ Database Tables
- customer  
- loan_application  
- loan_eligibility  
- loan_account  
- emi_schedule  

---

## ▶️ HOW TO RUN & TEST

### 1. Setup Database (PostgreSQL)
Create database:
```
loan_db
```

---

### 2. Configure Application

Update `src/main/resources/application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/loan_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

---

### 3. Run Application

Run:
```
LoanManagementApplication.java
```

OR:
```
mvn spring-boot:run
```

---

### 4. Open Swagger UI

```
http://localhost:8081/swagger-ui.html
```

---

### 5. Test API (Swagger)

- Open Swagger UI  
- Select POST /loan/apply  
- Click Try it out  
- Use:

```json
{
  "name": "Rama",
  "email": "rama@gmail.com",
  "income": 50000,
  "creditScore": 750,
  "amount": 50000,
  "tenure": 12
}
```

- Click Execute  

---

### 6. Test GET API

GET http://localhost:8081/loan/1

---

### 7. Tools Used
- Swagger UI → API testing & documentation  
- Postman → manual API testing  
- pgAdmin → database verification  

---

## 💡 Key Highlights
- Clean layered architecture (Controller → Service → Repository)  
- Full loan lifecycle implementation  
- DTO-based API design  
- Validation and global exception handling  
- Logging using SLF4J  
- Swagger API documentation  

---

## 📎 Author
Rama Chaitanya
