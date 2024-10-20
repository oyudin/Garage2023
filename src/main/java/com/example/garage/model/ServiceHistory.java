package com.example.garage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "service_history")  // Ссылка на таблицу в базе данных
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Указание на колонку id
    private Long id;
    @Column(name = "service_date", nullable = false)  // Поле model не может быть NULL
    private String service_date;
    @Column(name = "description", nullable = true)  // Поле license_plate не может быть NULL
    private String description;
    @Column(name = "mileage", nullable = true)  // Поле model не может быть NULL
    private Integer mileage;
    @Column(name = "price", nullable = false)  // Поле license_plate не может быть NULL
    private double price;
    @Column(name = "next_service_date", nullable = true)  // Поле model не может быть NULL
    private String next_service_date;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @JsonBackReference
    private Car car;
}
