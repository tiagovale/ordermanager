CREATE TABLE "Order" (
    id INT AUTO_INCREMENT PRIMARY KEY,
    creationDate TIMESTAMP NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (item_id) REFERENCES Item(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);
