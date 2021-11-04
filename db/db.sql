CREATE SEQUENCE todo_item_sequence START 1 INCREMENT 1;

CREATE TABLE todo_item (
    id INT PRIMARY KEY DEFAULT nextval('todo_item_sequence'),
    text varchar
);

ALTER SEQUENCE todo_item_sequence
    OWNED BY todo_item.id;