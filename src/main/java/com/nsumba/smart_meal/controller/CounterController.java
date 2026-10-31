package com.nsumba.smart_meal.controller;

import com.nsumba.smart_meal.model.Counter;
import com.nsumba.smart_meal.service.CounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/counters")
@RequiredArgsConstructor
public class CounterController {

    private final CounterService counterService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCounter(@RequestBody Counter counter){
        String returnedMsg = counterService.saveCounter(counter);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedMsg);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCounters(){
        List<Counter> allCounters = counterService.getCounters();
        return ResponseEntity.status(HttpStatus.OK).body(allCounters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCounterById(@PathVariable UUID id){
        Optional<Counter> counter = counterService.getCounterById(id);
        if(counter.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The counter with that id does not exist");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(counter.get());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCounter(@PathVariable UUID id, @RequestBody Counter counter){
        String returnedMsg = counterService.updateCounter(id, counter);
        if(returnedMsg.equals("The counter with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCounter(@PathVariable UUID id){
        String returnedMsg = counterService.deleteCounter(id);
        if(returnedMsg.equals("The counter with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    // C2
    @GetMapping("/free")
    public ResponseEntity<?> getFreeCounters(){
        List<Counter> counters = counterService.getFreeCounters();
        return ResponseEntity.status(HttpStatus.OK).body(counters);
    }
}
