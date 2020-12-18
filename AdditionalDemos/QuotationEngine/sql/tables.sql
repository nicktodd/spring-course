create database conygre;
use conygre;


CREATE TABLE `quotations` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `last_name` varchar(45) NOT NULL,
                              `first_name` varchar(45) NOT NULL,
                              `quote` double NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
SELECT * FROM conygre.quotations;
