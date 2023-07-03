package com.example.garage.repository;

public enum SQLQueries {
    SELECT_ALL_PERSONS_WITH_CARS("SELECT * FROM persons p INNER JOIN Cars c " +
            "ON p.id = c.person_id ORDER BY c.person_id;"),

    SELECT_ALL_PERSONS("SELECT * FROM persons;"),

    SELECT_PERSON_BY_ID("SELECT * FROM persons WHERE id = "),

    SAVE_PERSON("INSERT INTO persons (name, surname, phoneNumber) VALUES (?, ?, ?)"),

    DELETE_PERSON("DELETE FROM persons WHERE id = "),

    UPDATE_PERSON("UPDATE persons SET name = ?, surname = ?, phoneNumber WHERE id = "),

    //    ADD_CAR_TO_PERSON("UPDATE cars SET person_id = ? WHERE car_id = ?"),
    ADD_CAR_TO_PERSON("UPDATE cars SET person_id = ? WHERE car_id = ?"),

    SELECT_ALL_CARS("SELECT * FROM cars"),

    SAVE_CAR("INSERT INTO cars (number, vincode, brand, model, person_id) VALUES ( ?, ?, ?, ?, ?)"),

    UPDATE_CAR("UPDATE cars SET number = ?, vincode = ?, brand = ?, model = ? WHERE car_id = "),
    CHANGE_CAR_OWNER("UPDATE cars SET person_id = ? WHERE car_id = "),

    DELETE_CAR("DELETE FROM cars WHERE car_id = "),

    PERSON_CARS("SELECT FROM cars WHERE person_id = ");

    public final String query;

    SQLQueries(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return query;
    }
}
