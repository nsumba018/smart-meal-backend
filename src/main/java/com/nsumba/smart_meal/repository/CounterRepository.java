package com.nsumba.smart_meal.repository;

import com.nsumba.smart_meal.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface CounterRepository extends JpaRepository<Counter, UUID> {
    @Transactional
    @Modifying
    @Query("update Counter c set c.counterNumber = ?1, c.name = ?2 where c.id = ?3")
    int updateCounterById(int counterNumber, String name, UUID id);


}
