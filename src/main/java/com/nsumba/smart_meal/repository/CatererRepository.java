package com.nsumba.smart_meal.repository;

import com.nsumba.smart_meal.model.Caterer;
import com.nsumba.smart_meal.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatererRepository extends JpaRepository<Caterer, UUID> {
    @Transactional
    @Modifying
    @Query("update Caterer c set c.firstName = ?1, c.lastName = ?2, c.phone = ?3 where c.id = ?4")
    int updateCatererById(String firstName, String lastName, String phone, @NonNull UUID id);

    // D4
    boolean existsByCounter(Counter counter);

    // C1
    @Query("select c from Caterer c join c.cuisines cu where lower(cu.name) = lower(?1)")
    List<Caterer> findCaterersByCuisine(String name);

}
