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
    private double autoPartPrice;
    private String AutoPartDescription;
}
