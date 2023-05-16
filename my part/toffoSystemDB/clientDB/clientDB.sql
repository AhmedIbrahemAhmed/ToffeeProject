CREATE TABLE IF NOT EXISTS Client(
    clientID INTEGER64 PRIMARY KEY,
    name  VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    loyalityPoints INT,
    email VARCHAR(100) NOT NULL,
    status VARCHAR(100) NOT NULL
);
