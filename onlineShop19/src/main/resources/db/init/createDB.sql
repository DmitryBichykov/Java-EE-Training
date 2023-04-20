CREATE TABLE customer(
user_id INT NOT NULL,
login VARCHAR(50) NOT NULL,
password VARCHAR(20),
PRIMARY KEY(user_id)
);

CREATE TABLE good(
good_id INT NOT NULL,
title VARCHAR(50) NOT NULL,
price FLOAT NOT NULL,
PRIMARY KEY(good_id)
);

CREATE TABLE orders(
order_id INT NOT NULL,
user_id INT NOT NULL,
total_price FLOAT NOT NULL,
PRIMARY KEY(order_id),
FOREIGN KEY (user_id) REFERENCES customer(user_id)
);

CREATE TABLE order_good(
id INT NOT NULL AUTO_INCREMENT,
order_id INT NOT NULL,
good_id INT NOT NULL,
PRIMARY KEY(id),
FOREIGN KEY(order_id) REFERENCES orders(order_id),
FOREIGN KEY(good_id) REFERENCES good(good_id)
);
