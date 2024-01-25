CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       password VARCHAR(255),
                       firstName VARCHAR(255),
                       lastName VARCHAR(255),
                       gender VARCHAR(255),
                       address VARCHAR(255),
                       age TINYINT CHECK (age > 18),
                       isActive BOOLEAN DEFAULT true,
                       email VARCHAR(255) UNIQUE,
                       phoneNumber VARCHAR(20),
                       `role` varchar(50)
);