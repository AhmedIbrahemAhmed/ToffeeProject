CREATE TABLE IF NOT EXISTS Item(
    itemID INTEGER64 PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    brand VARCHAR(50) NOT NULL,
    unitType VARCHAR(50) NOT NULL, 
    description TEXT  NOT NULL,
    status VARCHAR(50) NOT NULL,
    price REAL NOT NULL,
    discount REAL NOT NULL,
    quantity INTEGER NOT NULL
);
