CREATE TABLE IF NOT EXISTS users
(
    id       bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    username text   NOT NULL UNIQUE,
    password text   NOT NULL,
    role     text   NOT NULL,
    PRIMARY KEY (id)
);

-- INSERT INTO users
-- VALUES (DEFAULT, 'user2', '$2a$12$qMMkBmFDBJGl2jqXH1nb5eqhrx4P.VYdHuMWYYm9ntEEEc3thH8/C', 'ADMIN');
