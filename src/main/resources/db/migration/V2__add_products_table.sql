CREATE TABLE products (
    id bigserial primary key,
    account_number varchar(255) not null unique,
    balance decimal not null,
    type varchar(20) not null,
    user_id bigint not null,
    constraint fk_user foreign key (user_id) references users(id)
);

INSERT INTO products (account_number, balance, type, user_id) VALUES
('123456789qw', 100, 'account', 2),
('123456789qr', 200, 'card', 2),
('123456789qt', 300, 'account', 3),
('123456789qy', 500, 'card', 3),
('123456789qu', 600, 'account', 4),
('123456789qi', 700, 'card', 4);