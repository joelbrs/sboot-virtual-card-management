-- Módulo Usuários
CREATE SCHEMA IF NOT EXISTS "user";
CREATE SCHEMA IF NOT EXISTS invoice;
CREATE SCHEMA IF NOT EXISTS virtual_card;
CREATE SCHEMA IF NOT EXISTS "transaction";
CREATE SCHEMA IF NOT EXISTS cashback;
CREATE SCHEMA IF NOT EXISTS audit_log;

CREATE TABLE IF NOT EXISTS "user".users (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    credit_limit DECIMAL(10, 2) NOT NULL,
    is_blocked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_user_cpf ON "user".users (cpf);
CREATE INDEX IF NOT EXISTS idx_user_email ON "user".users (email);

-- Módulo Cartões Virtuais
CREATE TYPE virtual_card.card_status AS ENUM ('ACTIVE', 'INACTIVE', 'BLOCKED');

CREATE TABLE IF NOT EXISTS virtual_card.virtual_cards (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id UUID NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    cvv VARCHAR(4) NOT NULL,
    expiration_date DATE NOT NULL,
    alias VARCHAR(255),
    credit_limit DECIMAL(10, 2) NOT NULL,
    available_limit DECIMAL(10, 2) NOT NULL,
    status virtual_card.card_status NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES "user".users(id)
);

CREATE INDEX IF NOT EXISTS idx_virtual_card_user_id ON virtual_card.virtual_cards (user_id);

-- Módulo Faturas
CREATE TYPE invoice.invoice_status AS ENUM ('OPEN', 'PAID', 'PARTIAL', 'OVERDUE');

CREATE TABLE IF NOT EXISTS invoice.invoices (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id UUID NOT NULL,
    card_id UUID NOT NULL,
    billing_period DATE NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    amount_paid DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    payment_date TIMESTAMP WITH TIME ZONE,
    status invoice.invoice_status NOT NULL,

    FOREIGN KEY (user_id) REFERENCES "user".users(id),
    FOREIGN KEY (card_id) REFERENCES virtual_card.virtual_cards(id)
);

CREATE INDEX IF NOT EXISTS idx_invoice_user_id ON invoice.invoices (user_id);
CREATE INDEX IF NOT EXISTS idx_invoice_card_id ON invoice.invoices (card_id);
CREATE INDEX IF NOT EXISTS idx_invoice_billing_period ON invoice.invoices (billing_period);

-- Módulo Transações
CREATE TYPE "transaction".transaction_category AS ENUM ('FOOD', 'SERVICE', 'OTHER');
CREATE TYPE "transaction".transaction_status AS ENUM ('APPROVED', 'DECLINED', 'REVERSED');

CREATE TABLE IF NOT EXISTS "transaction".transactions (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    card_id UUID NOT NULL,
    description VARCHAR(255) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    category "transaction".transaction_category NOT NULL,
    status "transaction".transaction_status NOT NULL,
    transaction_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    invoice_id UUID,
    cashback_id UUID UNIQUE,

    FOREIGN KEY (card_id) REFERENCES virtual_card.virtual_cards(id),
    FOREIGN KEY (invoice_id) REFERENCES invoice.invoices(id)
);

CREATE INDEX IF NOT EXISTS idx_transaction_card_id ON "transaction".transactions (card_id);
CREATE INDEX IF NOT EXISTS idx_transaction_invoice_id ON "transaction".transactions (invoice_id);

-- Módulo Cashback
CREATE TABLE IF NOT EXISTS cashback.cashbacks (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id UUID NOT NULL,
    transaction_id UUID UNIQUE,
    amount DECIMAL(10, 2) NOT NULL,
    received_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_redeemed BOOLEAN NOT NULL DEFAULT FALSE,

    FOREIGN KEY (user_id) REFERENCES "user".users(id),
    FOREIGN KEY (transaction_id) REFERENCES "transaction".transactions(id)
);

CREATE INDEX IF NOT EXISTS idx_cashback_user_id ON cashback.cashbacks (user_id);
CREATE INDEX IF NOT EXISTS idx_cashback_transaction_id ON cashback.cashbacks (transaction_id);

-- Módulo Auditoria
CREATE TABLE IF NOT EXISTS audit_log.audit_logs (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id UUID,
    action VARCHAR(255) NOT NULL,
    details TEXT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES "user".users(id)
);

CREATE INDEX IF NOT EXISTS idx_audit_log_user_id ON audit_log.audit_logs (user_id);
CREATE INDEX IF NOT EXISTS idx_audit_log_created_at ON audit_log.audit_logs (created_at);