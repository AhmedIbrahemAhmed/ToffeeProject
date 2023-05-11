
CREATE TABLE IF NOT EXISTS `Order`(
    orderID INTEGER64 PRIMARY KEY,
    cartID INTEGER64 NOT NULL,
    clientID INTEGER64 NOT NULL,
    orderPrice REAL NOT NULL,
    orderAddress VARCHAR(100) NOT NULL,
    checkoutDate VARCHAR(50) NOT NULL,
    paymentStatus VARCHAR(50) NOT NULL,
    orderStatus VARCHAR(50) NOT NULL
);  
