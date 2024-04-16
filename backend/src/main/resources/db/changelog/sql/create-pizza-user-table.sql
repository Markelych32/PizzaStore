DROP TABLE IF EXISTS pizza;
CREATE TABLE pizza
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(256) UNIQUE NOT NULL,
    price REAL NOT NULL,
    description VARCHAR(1024) NOT NULL,
    img_link VARCHAR NOT NULL
);