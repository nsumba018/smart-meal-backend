package com.nsumba.smart_meal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Entity
@Table(name = "employee")
@Getter @Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "staff_number", unique = true)
    private String staffNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "department")
    private String department;
}
