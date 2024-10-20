package com.example.garage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clients")  // Ссылка на таблицу в базе данных
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // Указание на колонку id
    private Long id;

    @Column(name = "name", nullable = false)  // Поле name не может быть NULL
    private String name;

    @Column(name = "surname", nullable = false)  // Поле surname не может быть NULL
    private String surname;

    @Column(name = "phone_number")  // Поле phoneNumber соответствует типу bigint
    private Long phoneNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true) // Связь один ко многим
    @JsonManagedReference
    private List<Car> cars; // Список машин
}
