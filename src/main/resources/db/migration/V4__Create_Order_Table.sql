CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    creation_date TIMESTAMP,
    item_id BIGINT,
    quantity INTEGER,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
