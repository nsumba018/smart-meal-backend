package com.nsumba.smart_meal.repository;

import com.nsumba.smart_meal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Transactional
    @Modifying
    @Query("update Employee e set e.firstName = ?1, e.lastName = ?2, e.staffNumber = ?3, e.email = ?4, e.department = ?5 where e.id = ?6")
    int updateEmployeeById(String firstName, String lastName, String staffNumber, String email, String department, UUID id);

    // B1
    List<Employee> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    // C4
    @Query("select distinct m.employee from MealBooking m where m.caterer.id = ?1")
    List<Employee> findEmployeesOfCaterer(UUID catererId);
}
