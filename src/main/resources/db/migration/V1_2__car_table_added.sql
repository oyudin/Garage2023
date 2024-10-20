CREATE TABLE IF NOT EXISTS cars
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number TEXT,
    brand TEXT,
    model TEXT,
    color TEXT,
    client_id BIGINT REFERENCES clients(id) ON DELETE CASCADE
);
