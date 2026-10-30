package com.nsumba.smart_meal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "caterer")
@Getter
@Setter
@NoArgsConstructor
public class Caterer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "counter_id", nullable = true)
    private Counter counter;

    @ManyToMany
    @JoinTable(name = "caterer_cuisine",
            joinColumns = @JoinColumn(name = "caterer_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_id")
    )
    private List<Cuisine> cuisines = new ArrayList<>();

}
