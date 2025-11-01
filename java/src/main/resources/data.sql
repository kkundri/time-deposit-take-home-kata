

-- Insert sample time deposits
INSERT INTO TIME_DEPOSITS (id,plan_type, days, balance) VALUES (1,'basic', 60, 10000.00);
INSERT INTO TIME_DEPOSITS (id,plan_type, days, balance) VALUES (2,'student', 120, 5000.00);
INSERT INTO TIME_DEPOSITS (id,plan_type, days, balance) VALUES (3,'premium', 90, 20000.00);


-- Insert sample withdrawals
INSERT INTO WITHDRAWALS (id,amount, date, time_deposit_id) VALUES (1,500.00, '2025-10-01', 1);
INSERT INTO WITHDRAWALS (id,amount, date, time_deposit_id) VALUES (2,200.00, '2025-10-20', 1);
INSERT INTO WITHDRAWALS (id,amount, date, time_deposit_id) VALUES (3,1000.00, '2025-09-15', 2);
INSERT INTO WITHDRAWALS (id,amount, date, time_deposit_id) VALUES (4,1000.00, '2025-09-30', 3);