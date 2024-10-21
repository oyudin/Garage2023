CREATE TABLE IF NOT EXISTS Service_History
(
    id                BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    service_date      TEXT   NOT NULL,
    description       TEXT,
    mileage           INT,
    price             DECIMAL(10, 2),
    next_service_date TEXT,
    car_id            BIGINT NOT NULL,
    FOREIGN KEY (car_id) REFERENCES cars (id)
);