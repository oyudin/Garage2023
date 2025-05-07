CREATE TABLE IF NOT EXISTS clients
(
    id          bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name        text   NOT NULL,
    surname     text   NOT NULL,
    phone_number bigint,
    primary key (id)
);
