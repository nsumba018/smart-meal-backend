package com.nsumba.smart_meal.controller;

import com.nsumba.smart_meal.model.PayrollDeduction;
import com.nsumba.smart_meal.service.PayrollDeductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payroll-deductions")
@RequiredArgsConstructor
public class PayrollDeductionController {

    private final PayrollDeductionService payrollDeductionService;

    // E1
    @PostMapping("/save")
    public ResponseEntity<String> savePayrollDeduction(@RequestBody PayrollDeduction payrollDeduction,
                                                       @RequestParam UUID mealBookingId){
        String returnedMsg = payrollDeductionService.savePayrollDeduction(payrollDeduction, mealBookingId);
        if(returnedMsg.equals("The meal booking with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }else if(returnedMsg.equals("Payroll deductions are only for SERVED meal bookings")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnedMsg);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(returnedMsg);
        }
    }

    // E2
    @GetMapping("/employee/{staffNumber}")
    public ResponseEntity<?> getPayrollDeductionsOfEmployee(@PathVariable String staffNumber){
        List<PayrollDeduction> deductions = payrollDeductionService.getPayrollDeductionsOfEmployee(staffNumber);
        return ResponseEntity.status(HttpStatus.OK).body(deductions);
    }

    // E3
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePayrollDeduction(@PathVariable UUID id){
        String returnedMsg = payrollDeductionService.deletePayrollDeduction(id);
        if(returnedMsg.equals("The payroll deduction with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }
}
