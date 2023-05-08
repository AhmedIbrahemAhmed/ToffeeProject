ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/final_phase/toffo/ToffoSystem/toffoSystemDB/orderDB/orderDB.db' AS "db1";
ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/final_phase/toffo/ToffoSystem/toffoSystemDB/itemDB/itemDB.db' AS "db2";
ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/final_phase/toffo/ToffoSystem/toffoSystemDB/cartDB/cartDB.db' AS "db3";

SELECT *
FROM db1.`Order`    
INNER JOIN db3.Cart ON db3.Cart.orderID = db1.`Order`.orderID
INNER JOIN db2.Item ON db3.Cart.itemID = db2.Item.itemID;

