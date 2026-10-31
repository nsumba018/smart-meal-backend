package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.repository.*;
import com.nsumba.smart_meal.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CatererRepository catererRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private MealBookingRepository mealBookingRepository;

    @Autowired
    private PayrollDeductionRepository payrollDeductionRepository;

    @Override
    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("employees", employeeRepository.count());
        summary.put("caterers", catererRepository.count());
        summary.put("freeCounters", (long) counterRepository.findFreeCounters().size());
        summary.put("bookingsToday", mealBookingRepository.countByMealDate(LocalDate.now()));
        summary.put("booked", mealBookingRepository.countByStatus(BookingStatus.BOOKED));
        summary.put("deductions", payrollDeductionRepository.count());
        return summary;
    }
}
