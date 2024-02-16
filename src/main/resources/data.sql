
INSERT INTO Users (name, email) VALUES ('Tiago Vale', 'tiagovale88@gmail.com');
INSERT INTO Users (name, email) VALUES ('Ana Clara', 'dantas-clara@hotmail.com');
INSERT INTO Users (name, email) VALUES ('Raimundo Pinheiro', 'pinheiro.ras@gmail.com');

INSERT INTO Item (name) VALUES ('Banana');
INSERT INTO Item (name) VALUES ('Apple');
INSERT INTO Item (name) VALUES ('Orange');

INSERT INTO StockMovement (creation_date, item_id, quantity)  VALUES ('2022-02-11 12:30:00', 1, 10);
INSERT INTO Orders (creation_date, item_id, quantity, user_id)  VALUES ('2022-02-11 12:30:00', 2, 20, 1);


