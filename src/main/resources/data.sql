-- Create table loan_store
CREATE TABLE loan_store (
    loan_id VARCHAR(255) PRIMARY KEY,
    customer_id VARCHAR(255),
    lender_id VARCHAR(255),
    amount DOUBLE,
    remaining_amount DOUBLE,
    payment_date DATE,
    interest_per_day DOUBLE,
    due_date DATE,
    penalty_per_day DOUBLE,
    cancel BOOLEAN
);

-- Insert data into table loan_store start
INSERT INTO loan_store (loan_id, customer_id, lender_id, amount, remaining_amount, payment_date, interest_per_day, due_date, penalty_per_day, cancel)
VALUES ('L1', 'C1', 'LEN1', 10000, 10000, '2023-06-05', 0.0001, '2023-07-05', 0.0001, false);

INSERT INTO loan_store (loan_id, customer_id, lender_id, amount, remaining_amount, payment_date, interest_per_day, due_date, penalty_per_day, cancel)
VALUES ('L2', 'C1', 'LEN1', 20000, 5000, '2023-06-01', 0.0001, '2023-08-05', 0.0001, false);

INSERT INTO loan_store (loan_id, customer_id, lender_id, amount, remaining_amount, payment_date, interest_per_day, due_date, penalty_per_day, cancel)
VALUES ('L3', 'C2', 'LEN2', 50000, 30000, '2023-04-04', 0.0002, '2023-05-04', 0.0002, false);

INSERT INTO loan_store (loan_id, customer_id, lender_id, amount, remaining_amount, payment_date, interest_per_day, due_date, penalty_per_day, cancel)
VALUES ('L4', 'C3', 'LEN2', 50000, 30000, '2023-04-04', 0.0002, '2023-05-04', 0.0002, false);
-- Insert data into table loan_store end