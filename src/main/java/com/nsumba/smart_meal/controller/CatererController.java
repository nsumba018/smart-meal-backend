package com.nsumba.smart_meal.controller;

import com.nsumba.smart_meal.model.Caterer;
import com.nsumba.smart_meal.service.CatererService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/caterers")
@RequiredArgsConstructor
public class CatererController {

    private final CatererService catererService;

    @PostMapping("/save")
    public ResponseEntity<String> saveCaterer(@RequestBody Caterer caterer) {
        String returnedMsg = catererService.saveCaterer(caterer);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedMsg);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getCaterers(){
        List<Caterer> allCaterers = catererService.getCaterers();
        return ResponseEntity.status(HttpStatus.OK).body(allCaterers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatererById(@PathVariable UUID id){
        Optional<Caterer> caterer = catererService.getCatererById(id);
        if(caterer.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The caterer with that id does not exist");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(caterer.get());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCaterer(@PathVariable UUID id, @RequestBody Caterer caterer){
        String returnedMsg = catererService.updateCaterer(id, caterer);
        if (returnedMsg.equals("The caterer with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCaterer(@PathVariable UUID id){
        String returnedMsg = catererService.deleteCaterer(id);
        if(returnedMsg.equals("The caterer with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(returnedMsg);
        }
        return ResponseEntity.status(HttpStatus.OK).body(returnedMsg);
    }

}

