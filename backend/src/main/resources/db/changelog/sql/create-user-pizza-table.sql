DROP TABLE IF EXISTS user_pizza;
CREATE TABLE user_pizza
(
    user_id BIGINT REFERENCES _user(id),
    pizza_id BIGINT REFERENCES pizza(id),
    PRIMARY KEY (user_id, pizza_id)
)