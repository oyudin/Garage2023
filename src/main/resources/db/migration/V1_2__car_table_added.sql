CREATE TABLE IF NOT EXISTS cars
(
    car_id    bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    number    text,
    brand     text,
    model     text,
    color     text,
    person_id bigint references persons (id) ON DELETE CASCADE,
    primary key (car_id)
);