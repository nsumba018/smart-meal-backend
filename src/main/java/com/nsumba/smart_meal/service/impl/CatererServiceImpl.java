package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.Caterer;
import com.nsumba.smart_meal.model.Counter;
import com.nsumba.smart_meal.model.Cuisine;
import com.nsumba.smart_meal.repository.CatererRepository;
import com.nsumba.smart_meal.repository.CounterRepository;
import com.nsumba.smart_meal.repository.CuisineRepository;
import com.nsumba.smart_meal.service.CatererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatererServiceImpl implements CatererService {
    @Autowired
    private CatererRepository catererRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public String saveCaterer(Caterer caterer) {
        catererRepository.save(caterer);
        return "Caterer saved successfully";
    }

    @Override
    public List<Caterer> getCaterers() {
        List<Caterer> allCaterers = catererRepository.findAll();
        return allCaterers;
    }

    @Override
    public Optional<Caterer> getCatererById(UUID id) {
        return catererRepository.findById(id);
    }

    @Override
    public String updateCaterer(UUID id, Caterer caterer) {
        if(!catererRepository.existsById(id)){
            return "The caterer with that id does not exist";
        }else{
            catererRepository.updateCatererById(caterer.getFirstName(),caterer.getLastName(), caterer.getPhone(), id);
            return "Caterer updated successfully";
        }

    }

    @Override
    public String deleteCaterer(UUID id) {
        Optional<Caterer> caterer = catererRepository.findById(id);
        if(caterer.isEmpty()){
            return "The caterer with that id does not exist";
        }else{
            catererRepository.deleteById(id);
            return "Caterer deleted successfully";
        }

    }

    // C1
    @Override
    public List<Caterer> getCaterersByCuisine(String name) {
        return catererRepository.findCaterersByCuisine(name);
    }

    @Override
    public boolean catererExists(UUID id) {
        return catererRepository.existsById(id);
    }

    // D3
    @Override
    @Transactional
    public String addCuisineToCaterer(UUID catererId, UUID cuisineId) {
        Optional<Caterer> findCaterer = catererRepository.findById(catererId);
        Optional<Cuisine> cuisine = cuisineRepository.findById(cuisineId);
        if(findCaterer.isEmpty()){
            return "The caterer with that id does not exist";
        }else if(cuisine.isEmpty()){
            return "That cuisine does not exist";
        }else{
            Caterer caterer = findCaterer.get();
            if(caterer.getCuisines().contains(cuisine.get())){
                return "The caterer already has that cuisine";
            }
            caterer.getCuisines().add(cuisine.get());
            catererRepository.save(caterer);
            return "Cuisine added to the caterer";
        }
    }

    // D4
    @Override
    public String changeCatererCounter(UUID catererId, int counterNumber) {
        Optional<Caterer> findCaterer = catererRepository.findById(catererId);
        Optional<Counter> counter = counterRepository.findByCounterNumber(counterNumber);
        if(findCaterer.isEmpty()){
            return "The caterer with that id does not exist";
        }else if(counter.isEmpty()){
            return "The counter with that counterNumber does not exist";
        }
        Caterer caterer = findCaterer.get();
        if(catererRepository.existsByCounter(counter.get())){
            if(caterer.getCounter() == null || !caterer.getCounter().getId().equals(counter.get().getId())){
                return "That counter is taken by another caterer";
            }
        }
        caterer.setCounter(counter.get());
        catererRepository.save(caterer);
        return "Counter changed successfully";
    }
}
