DROP TABLE IF EXISTS request_list_items;
DROP TABLE IF EXISTS recycle_requests;
DROP TABLE IF EXISTS recycle_items;
DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20),
    password VARCHAR(20),
    total_points INT
);

CREATE TABLE recycle_items(
    name VARCHAR(20) NOT NULL PRIMARY KEY,
    points INT
);

CREATE TABLE recycle_requests(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    username VARCHAR(20),
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE request_list_items(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    quantity INT NOT NULL,
    recycle_request_id INT NOT NULL,
    FOREIGN KEY(recycle_request_id) REFERENCES recycle_requests(id)
)