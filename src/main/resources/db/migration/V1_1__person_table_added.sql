CREATE TABLE IF NOT EXISTS persons
(
    id      bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name    text   NOT NULL,
    surname text   NOT NULL,
    primary key (id)
);