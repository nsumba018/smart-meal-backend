package com.nsumba.smart_meal.repository;

import com.nsumba.smart_meal.model.PayrollDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PayrollDeductionRepository extends JpaRepository<PayrollDeduction, UUID> {

    // E2
    @Query("select p from PayrollDeduction p where p.mealBooking.employee.staffNumber = ?1 order by p.deductionDate desc")
    List<PayrollDeduction> findPayrollDeductionsOfEmployee(String staffNumber);

    // E3
    boolean existsByMealBookingId(UUID mealBookingId);
}
