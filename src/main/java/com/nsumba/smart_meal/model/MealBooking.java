package com.nsumba.smart_meal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "mealbooking")
@Getter
@Setter
@NoArgsConstructor
public class MealBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "meal_date")
    private LocalDate mealDate;

    @Column(name = "status")
    private BookingStatus status;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @Column(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @Column(name = "caterer_id")
    private Caterer caterer;
}
