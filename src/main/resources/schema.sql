CREATE TABLE IF NOT EXISTS product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    image_url VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS member (
     id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
     email VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS coupon (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    discount_type VARCHAR(255) NOT NULL,
    discount_percent DOUBLE NOT NULL,
    discount_amount INT NOT NULL,
    minimum_price INT NOT NULL
);

CREATE TABLE IF NOT EXISTS member_coupon (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    coupon_id BIGINT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (coupon_id) REFERENCES coupon(id)
);

CREATE TABLE IF NOT EXISTS cart_item (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE IF NOT EXISTS purchase_order (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    original_price INT NOT NULL,
    discount_price INT NOT NULL,
    used_coupon_id BIGINT,
    confirm_state BOOL NOT NULL,
    member_id BIGINT NOT NULL,
    FOREIGN KEY (used_coupon_id) REFERENCES coupon(id),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE IF NOT EXISTS order_product (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES purchase_order(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id)
);
