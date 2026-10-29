package com.nsumba.smart_meal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "counter")
@Getter @Setter
@NoArgsConstructor
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="counter_number")
    private int counterNumber;

    @Column(name = "name")
    private String name;
}
