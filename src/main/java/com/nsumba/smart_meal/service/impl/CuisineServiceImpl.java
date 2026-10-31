package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.Cuisine;
import com.nsumba.smart_meal.repository.CuisineRepository;
import com.nsumba.smart_meal.service.CuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CuisineServiceImpl implements CuisineService {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Override
    public String saveCuisine(Cuisine cuisine) {
        cuisineRepository.save(cuisine);
        return "Cuisine saved successfully";
    }

    @Override
    public List<Cuisine> getCuisines() {
        return cuisineRepository.findAll();
    }

    @Override
    public Optional<Cuisine> getCuisineById(UUID id) {
        return cuisineRepository.findById(id);
    }

    @Override
    public String updateCuisine(UUID id, Cuisine cuisine) {
        if(!cuisineRepository.existsById(id)){
            return "The cuisine with that id does not exist";
        }else{
            cuisineRepository.updateCuisineById(cuisine.getName(), id);
            return "Cuisine updated successfully";
        }
    }

    @Override
    public String deleteCuisine(UUID id) {
        if(!cuisineRepository.existsById(id)){
            return "The cuisine with that id does not exist";
        }else{
            cuisineRepository.deleteById(id);
            return "Cuisine deleted successfully";
        }
    }

    // C3
    @Override
    public List<Cuisine> getUnusedCuisines() {
        return cuisineRepository.findUnusedCuisines();
    }
}
