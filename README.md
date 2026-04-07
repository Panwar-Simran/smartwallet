# SmartWallet – Backend

**Technologies:** Java, Spring Boot, PostgreSQL, REST APIs, JWT Authentication, Postman

## Project Overview
SmartWallet is a **digital wallet application** that allows users to manage their money securely. 
The backend handles user registration, authentication, wallet operations, and transaction history. 
It ensures **secure and consistent transactions** using service-layer logic.

## Features
- User registration and login with **JWT authentication**
- Wallet operations: **add money, get balance, transfer money, view and edit user profile, view transaction history**
- REST APIs integrated with frontend for **real-time interaction**
- Manual timestamp management for transactions
- Efficient search functionality by **email or name**
- Custom exception handling for robust operation

## Project Structure
- **Controller:** Handles API endpoints
- **Service:** Business logic and timestamp management
- **Repository:** Database interactions
- **Model:** Entities for User, Wallet, and Transaction
- **DTO:** Request & Response objects

## Setup Instructions
1. Clone the repository:

````bash
git clone https://github.com/Panwar-Simran/smartwallet.git 
````

2. Create a local PostgreSQL database named smartwallet.

3. Create an application.properties file locally (not included in repo for security) with your DB credentials:
````
spring.datasource.url=jdbc:postgresql://localhost:5432/smartwallet
spring.datasource.username=<your-db-username>
spring.datasource.password=<your-db-password>
````

4. Open the project in IntelliJ IDEA or VS Code

5. Run the Spring Boot application

6. Test APIs using Postman (all endpoints available in Controllers)

[Backend Repo](https://github.com/Panwar-Simran/smartwallet)