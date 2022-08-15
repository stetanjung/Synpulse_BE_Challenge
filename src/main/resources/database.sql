USE synpulse_be;

CREATE TABLE user_account (
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user varchar(255) NOT NULL UNIQUE,
    account_iban varchar(255) NOT NULL UNIQUE,
    account_currency varchar(30) NOT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE transaction_records (
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    transaction_id varchar(255) NOT NULL UNIQUE,
    transaction_amount int NOT NULL,
    transaction_currency varchar(10) NOT NULL,
    transaction_type varchar(30) NOT NULL,
    account_iban varchar(255) NOT NULL,
    description varchar(255),
    transaction_date date NOT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
