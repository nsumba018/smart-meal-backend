package com.nsumba.smart_meal.service.impl;


import com.nsumba.smart_meal.model.Counter;
import com.nsumba.smart_meal.repository.CounterRepository;
import com.nsumba.smart_meal.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterRepository counterRepository;

    @Override
    public String saveCounter(Counter counter) {
        counterRepository.save(counter);
        return "Counter saved successfully";
    }

    @Override
    public List<Counter> getCounters() {
        List<Counter> counters = counterRepository.findAll();
        return counters;
    }

    @Override
    public Optional<Counter> getCounterById(UUID id) {
        return counterRepository.findById(id);
    }

    @Override
    public String updateCounter(UUID id, Counter counter) {
        if(!counterRepository.existsById(id)){
            return "The counter with that id does not exist";
        }else{
            counterRepository.updateCounterById(counter.getCounterNumber(), counter.getName(), id);
            return "Counter updated successfully";
        }
    }

    @Override
    public String deleteCounter(UUID id) {
        Optional<Counter> counter = counterRepository.findById(id);
        if(counter.isEmpty()){
            return "The counter with that id does not exist";
        }else{
            counterRepository.deleteById(id);
            return "Counter deleted successfully";
        }
    }

    // C2
    @Override
    public List<Counter> getFreeCounters() {
        return counterRepository.findFreeCounters();
    }
}
