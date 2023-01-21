CREATE TABLE persons
(
    id      bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name    text   NOT NULL,
    surname text   NOT NULL,
    primary key (id)
);

CREATE TABLE cars
(
    car_id    bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    number    text,
    brand     text,
    model     text,
    color     text,
    person_id bigint references persons(id),
    primary key (car_id)
);