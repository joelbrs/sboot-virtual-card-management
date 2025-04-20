## 💳 Virtual Credit Card System

---

### 🎯 Objective
Allow users to create and manage virtual credit cards, control expenses, view invoices and receive benefits such as cashback, in a secure manner.

---

## 🧩 Main Modules

1. User Registration and Authentication
2. Virtual Card Management
3. Transactions
4. Invoices
5. Cashback System
6. Security and Notifications

---

## 📌 1. User Registration and Authentication

### Requirements
- Registration with name, CPF, email and password.
- Login with JWT authentication.
- Password recovery via email.

### Business Rules
- The CPF must be unique in the system.
- Passwords stored with a secure hash (e.g. BCrypt).
- After 3 invalid login attempts, the user is blocked for 15 minutes.

---
## 📌 2. Virtual Card Management

### Requirements
- A user can create multiple virtual cards.
- Each card has: fictitious number, validity, CVV, limit, nickname and status (active/inactive).

### Business Rules
- The total limit of all cards cannot exceed the user's global limit.
- Inactive cards cannot be used.
- CVV and number must be generated automatically.

---
## 📌 3. Transactions

### Requirements
- Simulation of transactions with amount, description and card used.
- Event log with status (successful, declined, reversed).
- Complete transaction history.

### Business Rules
- Transactions above the available limit are declined.
- Transactions can be refunded within 7 days.
- Approved transactions are included in the current month's invoice.

---
## 📌 4. Invoices

### Requirements
- Monthly invoices by card, with total amount and list of transactions.
- Allows full or partial payment.
- Automatic generation on the 1st of each month.

### Business Rules
- Partial payments generate compound interest of 5% per month.
- After 3 days of delay, the card is blocked.
- Payment of the invoice releases the card limit.

---
## 📌 5. Cashback System

### Requirements
- Cashback percentage by category (e.g.: food = 2%, services = 1%).
- View accumulated cashback and withdrawal to account (mock).

### Business Rules
- Only transactions above 10.00 generate cashback. Minimum withdrawal amount: R$20.00.
- Reversed transactions do not generate cashback.

---
## 📌 6. Security and Notifications

### Requirements
- Transaction notification (email).
- Audit log of sensitive actions.
- Simulated integration with anti-fraud.

### Business Rules
- After 5 declines in less than 1 hour, the card is deactivated.
- Audit logs must be stored for at least 2 years.

---