package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.model.MealBooking;
import com.nsumba.smart_meal.repository.MealBookingRepository;
import com.nsumba.smart_meal.service.MealBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MealBookingServiceImpl implements MealBookingService {

    @Autowired
    private MealBookingRepository mealBookingRepository;

    @Override
    public String saveMealBooking(MealBooking mealBooking) {
        mealBookingRepository.save(mealBooking);
        return "Meal booking saved successfully";
    }

    @Override
    public List<MealBooking> getMealBookings() {
        return mealBookingRepository.findAll();
    }

    @Override
    public Optional<MealBooking> getMealBookingById(UUID id) {
        return mealBookingRepository.findById(id);
    }

    @Override
    public String updateMealBooking(UUID id, MealBooking mealBooking) {
        if(!mealBookingRepository.existsById(id)){
            return "The meal booking with that id does not exist";
        }else{
            mealBookingRepository.updateMealBookingById(mealBooking.getMealDate(),
                    mealBooking.getMealType(), mealBooking.getPrice(), id);
            return "Meal booking updated successfully";
        }
    }

    @Override
    public String deleteMealBooking(UUID id) {
        if(!mealBookingRepository.existsById(id)){
            return "The meal booking with that id does not exist";
        }else{
            mealBookingRepository.deleteById(id);
            return "Meal booking deleted successfully";
        }
    }

    // B2
    @Override
    public List<MealBooking> getMealBookingsByStatus(BookingStatus status) {
        return mealBookingRepository.findByStatusOrderByMealDateAsc(status);
    }

    // B3
    @Override
    public List<MealBooking> getMealBookingsBetween(LocalDate start, LocalDate end) {
        return mealBookingRepository.findByMealDateBetweenOrderByMealDateAsc(start, end);
    }
}
