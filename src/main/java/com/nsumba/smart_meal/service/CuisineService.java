package com.nsumba.smart_meal.service;

import com.nsumba.smart_meal.model.Cuisine;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CuisineService {
    String saveCuisine(Cuisine cuisine);
    List<Cuisine> getCuisines();
    Optional<Cuisine> getCuisineById(UUID id);
    String updateCuisine(UUID id, Cuisine cuisine);
    String deleteCuisine(UUID id);
    List<Cuisine> getUnusedCuisines();
}
