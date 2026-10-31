package com.nsumba.smart_meal.service;

import com.nsumba.smart_meal.model.PayrollDeduction;

import java.util.List;
import java.util.UUID;

public interface PayrollDeductionService {
    String savePayrollDeduction(PayrollDeduction payrollDeduction, UUID mealBookingId);
    List<PayrollDeduction> getPayrollDeductionsOfEmployee(String staffNumber);
    String deletePayrollDeduction(UUID id);
}
