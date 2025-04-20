## 🧩 Virtual Credit Card System

---

### 🧾 Main Entities

---

### **User**
| Field         | Type      | Description                          |
|---------------|-----------|--------------------------------------|
| id            | UUID (PK) | Unique identifier                    |
| name          | String    | Full name of the user                |
| cpf           | String    | Brazilian CPF (unique)              |
| email         | String    | Email address (unique)              |
| password      | String    | Encrypted password (hashed)         |
| credit_limit  | Decimal   | Global credit limit for the user    |
| is_blocked    | Boolean   | If user is temporarily blocked      |
| created_at    | Timestamp | Account creation date               |

**Relationships:**
- 1:N with `VirtualCard`
- 1:N with `Invoice`
- 1:N with `Cashback`

---

### **VirtualCard**
| Field             | Type      | Description                                 |
|-------------------|-----------|---------------------------------------------|
| id                | UUID (PK) | Unique identifier                           |
| user_id           | FK        | References `User`                           |
| card_number       | String    | Virtual card number                         |
| cvv               | String    | Security code (encrypted or short-lived)    |
| expiration_date   | Date      | Expiration date                             |
| alias             | String    | Friendly name for the card                  |
| credit_limit      | Decimal   | Card-specific limit                         |
| available_limit   | Decimal   | Remaining limit                             |
| status            | ENUM      | ACTIVE, INACTIVE, BLOCKED                   |
| created_at        | Timestamp | Creation timestamp                          |

**Relationships:**
- 1:N with `Transaction`
- 1:N with `Invoice`

---

### **Transaction**
| Field           | Type      | Description                                 |
|------------------|-----------|---------------------------------------------|
| id               | UUID (PK) | Unique identifier                           |
| card_id          | FK        | References `VirtualCard`                    |
| description      | String    | Description of the transaction              |
| amount           | Decimal   | Transaction amount                          |
| category         | ENUM      | FOOD, SERVICE, OTHER                        |
| status           | ENUM      | APPROVED, DECLINED, REVERSED                |
| transaction_date | Timestamp | Timestamp of the transaction                |

**Relationships:**
- N:1 with `Invoice`
- 1:1 with `Cashback` (optional)

---

### **Invoice**
| Field           | Type       | Description                                  |
|------------------|------------|----------------------------------------------|
| id               | UUID (PK)  | Unique identifier                            |
| user_id          | FK         | References `User`                            |
| card_id          | FK         | References `VirtualCard`                     |
| billing_period   | YearMonth  | Invoice reference period                     |
| total_amount     | Decimal    | Total invoice amount                         |
| amount_paid      | Decimal    | Amount already paid                          |
| payment_date     | Timestamp  | Date the invoice was paid (if applicable)    |
| status           | ENUM       | OPEN, PAID, PARTIAL, OVERDUE                 |

**Relationships:**
- 1:N with `Transaction`

---

### **Cashback**
| Field            | Type      | Description                                  |
|------------------|-----------|----------------------------------------------|
| id               | UUID (PK) | Unique identifier                            |
| user_id          | FK        | References `User`                            |
| transaction_id   | FK        | References `Transaction` (optional)         |
| amount           | Decimal   | Cashback amount                              |
| received_at      | Timestamp | Date cashback was received                   |
| is_redeemed      | Boolean   | Indicates if cashback was redeemed           |

---

### **AuditLog**
| Field       | Type      | Description                                  |
|-------------|-----------|----------------------------------------------|
| id          | UUID (PK) | Unique identifier                            |
| user_id     | FK        | References `User`                            |
| action      | String    | Action performed                             |
| details     | String    | Extra info (e.g., IP, user-agent)            |
| created_at  | Timestamp | Timestamp of the action                      |
