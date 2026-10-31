package com.nsumba.smart_meal.repository;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.model.MealBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface MealBookingRepository extends JpaRepository<MealBooking, UUID> {
    @Transactional
    @Modifying
    @Query("update MealBooking m set m.mealDate = ?1, m.mealType = ?2, m.price = ?3 where m.id = ?4")
    int updateMealBookingById(LocalDate mealDate, String mealType, double price, UUID id);

    // B2
    List<MealBooking> findByStatusOrderByMealDateAsc(BookingStatus status);

    // B3
    List<MealBooking> findByMealDateBetweenOrderByMealDateAsc(LocalDate start, LocalDate end);
}
