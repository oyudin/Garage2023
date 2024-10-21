package com.example.garage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cars")  // Ссылка на таблицу в базе данных
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Указание на колонку id
    private Long id;
    @Column(name = "number", nullable = false)  // Поле model не может быть NULL
    private String number;
    @Column(name = "vin_code", nullable = false)  // Поле license_plate не может быть NULL
    private String vin_code;
    @Column(name = "brand", nullable = false)  // Поле license_plate не может быть NULL
    private String brand;
    @Column(name = "model", nullable = false)  // Поле model не может быть NULL
    private String model;
    @ManyToOne // Указываем связь многие к одному
    @JoinColumn(name = "client_id", nullable = false) // Внешний ключ в таблице cars
    @JsonBackReference
    private Client client;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<ServiceHistory> serviceHistories;
}
