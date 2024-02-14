CREATE TABLE StockMovement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    creation_date TIMESTAMP NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (item_id) REFERENCES Item(id)
);
