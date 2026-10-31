package com.nsumba.smart_meal.controller;

import com.nsumba.smart_meal.model.BookingStatus;
import com.nsumba.smart_meal.model.MealBooking;
import com.nsumba.smart_meal.service.MealBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/meal-bookings")
@RequiredArgsConstructor
public class MealBookingContoller {

    private final MealBookingService mealBookingService;

    @PostMapping("/save")
    public ResponseEntity<String> saveMealBooking(@RequestBody MealBooking mealBooking){
        String returnedMsg = mealBookingService.saveMealBooking(mealBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedMsg);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMealBookings(){
        List<MealBooking> allMealBookings = mealBookingService.getMealBookings();
        return ResponseEntity.status(HttpStatus.OK).body(allMealBookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMealBookingById(@PathVariable UUID id){
        Optional<MealBooking> mealBooking = mealBookingService.getMealBookingById(id);
        if(mealBooking.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The meal booking with that id does not exist");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(mealBooking.get());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMealBooking(@PathVariable UUID id, @RequestBody MealBooking mealBooking){
        String returnedMsg = mealBookingService.updateMealBooking(id, mealBooking);
        if(returnedMsg.equals("The meal booking with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMealBooking(@PathVariable UUID id){
        String returnedMsg = mealBookingService.deleteMealBooking(id);
        if(returnedMsg.equals("The meal booking with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    // B2
    @GetMapping("/by-status")
    public ResponseEntity<?> getMealBookingsByStatus(@RequestParam BookingStatus status){
        List<MealBooking> mealBookings = mealBookingService.getMealBookingsByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(mealBookings);
    }

    // B3
    @GetMapping("/between")
    public ResponseEntity<?> getMealBookingsBetween(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<MealBooking> mealBookings = mealBookingService.getMealBookingsBetween(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(mealBookings);
    }
}
