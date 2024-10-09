package com.example.garage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("auto_parts")
public class AutoPart {
    @Id
    @Column("id")
    private Integer id;
    @Column("repairmentdate")
    private String repairmentDate;
    @Column("auto_part_price")
    private double autoPartPrice;
    @Column("auto_part_description")
    private String autoPartDescription;
    @Column("millage")
    private int millage;
    @Column("auto_part_car_id")
    private int autoPartCarId;
}
