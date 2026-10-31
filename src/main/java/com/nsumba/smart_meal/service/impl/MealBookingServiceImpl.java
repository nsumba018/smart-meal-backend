package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.model.MealBooking;
import com.nsumba.smart_meal.repository.MealBookingRepository;
import com.nsumba.smart_meal.repository.PayrollDeductionRepository;
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

    @Autowired
    private PayrollDeductionRepository payrollDeductionRepository;

    @Override
    public String saveMealBooking(MealBooking mealBooking) {
        // D1
        if(mealBooking.getMealDate().isBefore(LocalDate.now())){
            return "Meal booking date cannot be in the past";
        }
        boolean alreadyBooked = mealBookingRepository.existsByCatererIdAndMealDateAndStatusNot(
                mealBooking.getCaterer().getId(), mealBooking.getMealDate(), BookingStatus.CANCELLED);
        if(alreadyBooked){
            return "Caterer already has a meal booking on that date";
        }
        mealBooking.setStatus(BookingStatus.BOOKED);
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
        }else if(payrollDeductionRepository.existsByMealBookingId(id)){
            // E3
            return "That meal booking has payroll deductions, delete them first";
        }else{
            mealBookingRepository.deleteById(id);
            return "Meal booking deleted successfully";
        }
    }

    // D2
    @Override
    public String updateMealBookingStatus(UUID id, BookingStatus status) {
        Optional<MealBooking> mealBooking = mealBookingRepository.findById(id);
        if(mealBooking.isEmpty()){
            return "The meal booking with that id does not exist";
        }
        MealBooking found = mealBooking.get();
        BookingStatus oldStatus = found.getStatus();
        if(oldStatus == BookingStatus.BOOKED &&
                (status == BookingStatus.PREPARED || status == BookingStatus.CANCELLED)){
            found.setStatus(status);
            mealBookingRepository.save(found);
            return "Meal booking status updated successfully";
        }else if(oldStatus == BookingStatus.PREPARED &&
                (status == BookingStatus.SERVED || status == BookingStatus.CANCELLED)){
            found.setStatus(status);
            mealBookingRepository.save(found);
            return "Meal booking status updated successfully";
        }else{
            return "Cannot change status from " + oldStatus + " to " + status;
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
