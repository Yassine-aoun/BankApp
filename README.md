# Banking App

This is a simple banking application implemented in Java. It provides functionalities for managing users, accounts, and transactions. Users can deposit, withdraw, transfer funds between accounts, and view transaction history.

## Features
- **User Management:** Users can be added to the system with a username and password.
- **Account Management:** Accounts can be created for users with an initial balance.
- **Funds Operations:** Users can deposit and withdraw funds from their accounts. Funds can also be transferred between accounts.
- **Transaction History:** Users can view their transaction history.
- **Interest Calculation:** For savings accounts, interest can be added to the account balance.

## Components

### Models
- **User:** Represents a user with a username and password.
- **Account:** Represents a bank account associated with a user.
- **SavingsAccount:** Extends Account class and includes functionality for adding interest.
- **Transaction:** Represents a transaction between accounts.

### Services
- **Bank:** Provides functionalities for user and account management, fund operations, and transaction history.
- **AuthService:** Handles user authentication and token generation/validation using JSON Web Tokens (JWT).
- **ExternalAPIService:** Communicates with an external REST API for retrieving data (e.g., posts).

### Tests
- **BankTest:** Includes JUnit tests to validate the functionalities of the Bank class.

## How to Use
1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Run the tests in BankTest to ensure everything is functioning correctly.
4. You can use the provided functionalities or extend the application as needed for your banking application.

## Dependencies
- JUnit: For unit testing.
- Spring Web: For making HTTP requests to external APIs.

## Contributors
- Yassine Aoun - Initial implementation
