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
    private Integer id;
    @Column("auto_part_name")
    private String autoPartName;
    @Column("auto_part_price")
    private double autoPartPrice;
    @Column("auto_part_description")
    private String autoPartDescription;
    @Column("auto_part_car_id")
    private int autoPartCarId;
}
