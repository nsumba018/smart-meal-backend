package com.nsumba.smart_meal.controller;

import com.nsumba.smart_meal.model.Cuisine;
import com.nsumba.smart_meal.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cuisines")
@RequiredArgsConstructor
public class CuisineController {

    private final CuisineService cuisineService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCuisine(@RequestBody Cuisine cuisine){
        String returnedMsg = cuisineService.saveCuisine(cuisine);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedMsg);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCuisines(){
        List<Cuisine> allCuisines = cuisineService.getCuisines();
        return ResponseEntity.status(HttpStatus.OK).body(allCuisines);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCuisineById(@PathVariable UUID id){
        Optional<Cuisine> cuisine = cuisineService.getCuisineById(id);
        if(cuisine.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The cuisine with that id does not exist");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(cuisine.get());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCuisine(@PathVariable UUID id, @RequestBody Cuisine cuisine){
        String returnedMsg = cuisineService.updateCuisine(id, cuisine);
        if(returnedMsg.equals("The cuisine with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCuisine(@PathVariable UUID id){
        String returnedMsg = cuisineService.deleteCuisine(id);
        if(returnedMsg.equals("The cuisine with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    // C3
    @GetMapping("/unused")
    public ResponseEntity<?> getUnusedCuisines(){
        List<Cuisine> cuisines = cuisineService.getUnusedCuisines();
        return ResponseEntity.status(HttpStatus.OK).body(cuisines);
    }
}
