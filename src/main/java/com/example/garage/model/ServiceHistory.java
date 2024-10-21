package com.example.garage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_history")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "service_date", nullable = false)
    private String service_date;
    @Column(name = "description")
    private String description;
    @Column(name = "mileage", nullable = false)
    private Integer mileage;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "next_service_date")
    private String next_service_date;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @JsonBackReference
    private Car car;
}
