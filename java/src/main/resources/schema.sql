CREATE TABLE timeDeposits (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              planType VARCHAR(50) NOT NULL,
                              days INT NOT NULL,
                              balance DECIMAL(19,4) NOT NULL
);

CREATE TABLE withdrawals (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            amount DECIMAL(19,4) NOT NULL,
                            date DATE NOT NULL,
                            timeDepositId INT NOT NULL,
                            CONSTRAINT fk_time_deposit
                                FOREIGN KEY (timeDepositId)
                                    REFERENCES timeDeposits(id)
                                    ON DELETE CASCADE
);

-- Insert sample time deposits
INSERT INTO timeDeposits (planType, days, balance) VALUES ('Basic', 60, 10000.00);
INSERT INTO timeDeposits (planType, days, balance) VALUES ('Student', 120, 5000.00);
INSERT INTO timeDeposits (planType, days, balance) VALUES ('Premium', 90, 20000.00);

-- Insert sample withdrawals
INSERT INTO withdrawals (amount, date, timeDepositId) VALUES (500.00, '2025-10-01', 1);
INSERT INTO withdrawals (amount, date, timeDepositId) VALUES (200.00, '2025-10-20', 2);
INSERT INTO withdrawals (amount, date, timeDepositId) VALUES (1000.00, '2025-09-15', 3);