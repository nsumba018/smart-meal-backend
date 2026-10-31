package com.nsumba.smart_meal.service.impl;

import com.nsumba.smart_meal.model.Caterer;
import com.nsumba.smart_meal.repository.CatererRepository;
import com.nsumba.smart_meal.service.CatererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatererServiceImpl implements CatererService {
    @Autowired
    private CatererRepository catererRepository;

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
}
