package com.nsumba.smart_meal.service;

import com.nsumba.smart_meal.model.Counter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CounterService {
    String saveCounter(Counter counter);
    List<Counter> getCounters();
    Optional<Counter> getCounterById(UUID id);
    String updateCounter(UUID id, Counter counter);
    String deleteCounter(UUID id);
}
