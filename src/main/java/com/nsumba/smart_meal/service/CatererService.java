package com.nsumba.smart_meal.service;

import com.nsumba.smart_meal.model.Caterer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatererService {
    String saveCaterer(Caterer caterer);
    List<Caterer> getCaterers();
    Optional<Caterer> getCatererById(UUID id);
    String updateCaterer(UUID id, Caterer caterer);
    String deleteCaterer(UUID id);
    List<Caterer> getCaterersByCuisine(String name);
    boolean catererExists(UUID id);
    String addCuisineToCaterer(UUID catererId, UUID cuisineId);
    String changeCatererCounter(UUID catererId, int counterNumber);
}
