package com.nsumba.smart_meal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payroll_deduction")
@Getter
@Setter
@NoArgsConstructor
public class PayrollDeduction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "meal_item")
    private String mealItem;

    @Column(name = "note")
    private String note;

    @Column(name = "amount_rwf")
    private int amountRwf;

    @Column(name = "deduction_date")
    private LocalDate deductionDate;

    @ManyToOne
    @JoinColumn(name = "meal_booking_id")
    private MealBooking mealBooking;
}
