package com.nsumba.smart_meal.controller;

import com.nsumba.smart_meal.model.Employee;
import com.nsumba.smart_meal.service.CatererService;
import com.nsumba.smart_meal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CatererService catererService;

    @PostMapping("/save")
    public ResponseEntity<String> saveEmployee(@RequestBody Employee employee){
        String returnedMsg = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedMsg);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> allEmployees = employeeService.getEmployees();
        return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable UUID id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if(employee.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The employee with that id does not exist");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(employee.get());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable UUID id, @RequestBody Employee employee){
        String returnedMsg = employeeService.updateEmployee(id, employee);
        if(returnedMsg.equals("The employee with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable UUID id){
        String returnedMsg = employeeService.deleteEmployee(id);
        if(returnedMsg.equals("The employee with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    // B1
    @GetMapping("/by-last-name")
    public ResponseEntity<?> getEmployeesByLastName(@RequestParam String lastName){
        List<Employee> employees = employeeService.getEmployeesByLastName(lastName);
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    // C4
    @GetMapping("/of-caterer/{catererId}")
    public ResponseEntity<?> getEmployeesOfCaterer(@PathVariable UUID catererId){
        if(!catererService.catererExists(catererId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The caterer with that id does not exist");
        }
        List<Employee> employees = employeeService.getEmployeesOfCaterer(catererId);
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }
}
