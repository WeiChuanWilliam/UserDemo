- Drop table users if exists;


CREATE TABLE users (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(128) NOT NULL CHECK (LENGTH(name) >= 1 AND LENGTH(name) <= 128),
                           salary DOUBLE PRECISION NOT NULL CHECK (salary >= 0),
                           age INTEGER NOT NULL CHECK (age >= 0 AND age <= 135)
);

INSERT INTO users (name, salary, age) VALUES
                                              ('John Doe', 75000.00, 30),
                                              ('Jane Smith', 85000.50, 28),
                                              ('Michael Johnson', 95000.00, 45);

SELECT * FROM users;