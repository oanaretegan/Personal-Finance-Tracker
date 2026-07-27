# Personal Finance Tracker

A Java-based desktop application designed to help users manage their personal income, expenses, cash and card balances.

## Tech Stack
**Language:** Java
**UI Framework:** Java Swing
**Database:** Microsoft SQL Server
**Database Connectivity:** JDBC, Stored Procedures, PreparedStatement

## Key Features
**Authentication & Profile Management:** Log-in, registration, account deletion, and profile editing options (Name, Surname, Username, PIN).
**Balance Tracking:** Real-time visibility of card and cash balances, automatically updated with each transaction.
**Transaction Management:** Record incomes and expenses with details such as category, location, payment method, and date.
**Reports & Filtering:** View transactions filtered by various time ranges: Today, This Week, Current Month, Last 3/6 Months, This Year, or All-Time.
**Automatic Sorting:** Transactions are automatically sorted in descending order by date using Java `Comparator`.

## Architecture & Security
* Follows a clean layered architecture, separating the User Interface (UI), business logic, and database access.
* Protected against **SQL Injection** attacks by exclusively utilizing `PreparedStatement` and database `Stored Procedures`.



