CREATE TABLE users (
    id bigserial primary key,
    username varchar(255) unique
);

INSERT INTO users (username) VALUES
('Peter'),
('John'),
('Kate');