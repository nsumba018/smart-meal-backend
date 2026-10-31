package com.nsumba.smart_meal.repository;

import com.nsumba.smart_meal.model.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, UUID> {
    @Transactional
    @Modifying
    @Query("update Cuisine c set c.name = ?1 where c.id = ?2")
    int updateCuisineById(String name, UUID id);
}
