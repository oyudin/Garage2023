package com.example.garage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Garage {

    private int personId;
    private String name;
    private String surname;
    private int carId;
    private String number;
    private String brand;
    private String model;
    private String color;
    private int carPersonId;
}
