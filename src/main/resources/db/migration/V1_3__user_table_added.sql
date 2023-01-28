CREATE TABLE IF NOT EXISTS systemUsers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    username text NOT NULL UNIQUE,
    password text NOT NULL,
    PRIMARY KEY (id)
);