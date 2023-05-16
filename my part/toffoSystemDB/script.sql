ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/CS251-2023-S7,8-EngAhmed samir-20210379- ToffeeSDS-Code.1.0./Toffe_implementation/toffoSystemDB/orderDB/orderDB.db' AS "db1";
ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/CS251-2023-S7,8-EngAhmed samir-20210379- ToffeeSDS-Code.1.0./Toffe_implementation/toffoSystemDB/itemDB/itemDB.db' AS "db2";
ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/CS251-2023-S7,8-EngAhmed samir-20210379- ToffeeSDS-Code.1.0./Toffe_implementation/toffoSystemDB/clientDB/clientDB.db' AS "db3";
ATTACH DATABASE '/home/marwan/FCAI/Level2 second term/Software Engneering/Assigments/Assignment3/CS251-2023-S7,8-EngAhmed samir-20210379- ToffeeSDS-Code.1.0./Toffe_implementation/toffoSystemDB/cartDB/cartDB.db' AS "db4";

SELECT *
FROM `Order`
INNER JOIN Client ON Client.clientID = `Order`.clientID
INNER JOIN Cart ON Cart.cartID = `Order`.cartID
INNER JOIN Item ON Cart.itemID = Item.itemID 
WHERE Client.name  = 'starlight'
ORDER BY `Order`.checkoutDate DESC;


SELECT *
FROM Cart