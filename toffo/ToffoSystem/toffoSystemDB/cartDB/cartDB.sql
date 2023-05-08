ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/final_phase/toffo/ToffoSystem/toffoSystemDB/orderDB/orderDB.db' AS db1;
ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/final_phase/toffo/ToffoSystem/toffoSystemDB/itemDB/itemDB.db' AS db2;
ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/final_phase/toffo/ToffoSystem/toffoSystemDB/cartDB/cartDB.db' AS db3;

SELECT *
FROM db2.Item
INNER JOIN db3.Cart ON db2.Item.itemID = db3.Cart.itemID
INNER JOIN db1.`Order` ON db3.Cart.orderID = db1.`Order`.orderID
WHERE db1.`Order`.orderID = 41066279644408904;

CREATE TABLE  IF NOT EXISTS Cart (
    cartID INTEGER64 PRIMARY KEY ,
    itemID INTEGER NOT NULL,
    orderID INTEGER NOT NULL,
    FOREIGN KEY (itemID) REFERENCES Item(itemID),
    FOREIGN KEY (orderID) REFERENCES `Order`(orderID)
);

