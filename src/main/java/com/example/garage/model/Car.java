package com.example.garage.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String number;
    @Column("vincode")
    private String vinCode;
    private String brand;
    private String model;
    private int personId;
    private int autoPartId;
}
