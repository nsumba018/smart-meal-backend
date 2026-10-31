package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.model.MealBooking;
import com.nsumba.smart_meal.model.PayrollDeduction;
import com.nsumba.smart_meal.repository.MealBookingRepository;
import com.nsumba.smart_meal.repository.PayrollDeductionRepository;
import com.nsumba.smart_meal.service.PayrollDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PayrollDeductionServiceImpl implements PayrollDeductionService {

    @Autowired
    private PayrollDeductionRepository payrollDeductionRepository;

    @Autowired
    private MealBookingRepository mealBookingRepository;

    // E1
    @Override
    public String savePayrollDeduction(PayrollDeduction payrollDeduction, UUID mealBookingId) {
        Optional<MealBooking> mealBooking = mealBookingRepository.findById(mealBookingId);
        if(mealBooking.isEmpty()){
            return "The meal booking with that id does not exist";
        }else if(mealBooking.get().getStatus() != BookingStatus.SERVED){
            return "Payroll deductions are only for SERVED meal bookings";
        }else{
            payrollDeduction.setDeductionDate(LocalDate.now());
            payrollDeduction.setMealBooking(mealBooking.get());
            payrollDeductionRepository.save(payrollDeduction);
            return "Payroll deduction saved successfully";
        }
    }

    // E2
    @Override
    public List<PayrollDeduction> getPayrollDeductionsOfEmployee(String staffNumber) {
        return payrollDeductionRepository.findPayrollDeductionsOfEmployee(staffNumber);
    }

    // E3
    @Override
    public String deletePayrollDeduction(UUID id) {
        Optional<PayrollDeduction> payrollDeduction = payrollDeductionRepository.findById(id);
        if(payrollDeduction.isEmpty()){
            return "The payroll deduction with that id does not exist";
        }else{
            payrollDeductionRepository.deleteById(id);
            return "Payroll deduction deleted successfully";
        }
    }
}
