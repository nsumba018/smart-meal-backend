package com.nsumba.smart_meal.service;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.model.MealBooking;

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
    List<MealBooking> getMealBookingsByStatus(BookingStatus status);
    List<MealBooking> getMealBookingsBetween(LocalDate start, LocalDate end);
}
