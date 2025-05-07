CREATE TABLE IF NOT EXISTS cars
(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number TEXT,
    vin_code TEXT,
    brand TEXT,
    model TEXT,
    client_id BIGINT REFERENCES clients(id) ON DELETE CASCADE
);
