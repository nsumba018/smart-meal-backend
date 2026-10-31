package com.nsumba.smart_meal.service;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.model.MealBooking;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MealBookingService {
    String saveMealBooking(MealBooking mealBooking);
    List<MealBooking> getMealBookings();
    Optional<MealBooking> getMealBookingById(UUID id);
    String updateMealBooking(UUID id, MealBooking mealBooking);
    String deleteMealBooking(UUID id);
    String updateMealBookingStatus(UUID id, BookingStatus status);
    List<Object[]> countMealBookingsByStatus();
    String cancelDayOfCaterer(UUID catererId, LocalDate date);
    Page<MealBooking> getMealBookingsPage(Pageable pageable);
    List<MealBooking> getMealBookingsByStatus(BookingStatus status);
    List<MealBooking> getMealBookingsBetween(LocalDate start, LocalDate end);
}
