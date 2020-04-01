package com.example.restservice.controller;

import static org.springframework.util.ObjectUtils.isEmpty;

import com.example.restservice.Exceptions.CounterAlreadyExistsException;
import com.example.restservice.Exceptions.CounterNotFoundException;
import com.example.restservice.Exceptions.EmptyNameException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    private static final ConcurrentMap<String, AtomicLong> counters = new ConcurrentHashMap<>();

    @GetMapping("/counter")
    public @ResponseBody
    AtomicLong getCounter(@RequestParam(value = "name") String name) {

        AtomicLong counterValue = counters.get(name);
        if (isEmpty(counterValue)) {
            throw new CounterNotFoundException();
        }

        return counterValue;
    }

    @PutMapping("/counter")
    public @ResponseBody
    Long createCounter(@RequestParam(value = "name") String name) {

        if (isEmpty(name)) {
            throw new EmptyNameException();
        }

        AtomicLong counterValue = counters.get(name);
        if (!isEmpty(counterValue)) {
            throw new CounterAlreadyExistsException();
        }
        counterValue = new AtomicLong();
        counters.put(name, counterValue);
        return counterValue.get();
    }

    @PostMapping("/counter")
    public @ResponseBody
    Long incrementCounter(@RequestParam(value = "name") String name) {

        if (isEmpty(name)) {
            throw new EmptyNameException();
        }

        AtomicLong counterValue = counters.get(name);
        if (isEmpty(counterValue)) {
            throw new CounterNotFoundException();
        }
        return counterValue.incrementAndGet();
    }

    @DeleteMapping("/counter")
    public @ResponseBody
    void deleteCounter(@RequestParam(value = "name") String name) {

        if (isEmpty(name)) {
            throw new EmptyNameException();
        }

        AtomicLong counterValue = counters.get(name);
        if (isEmpty(counterValue)) {
            throw new CounterNotFoundException();
        }
        counters.remove(name);
    }
    
    @GetMapping("/counters_sum")
    public @ResponseBody
    Long getCountersSum() {

        return counters.values().stream().mapToLong(AtomicLong::get).sum();
    }

    @GetMapping("/counter_names")
    public @ResponseBody
    Set<String> getCounterNamesList() {

        return counters.keySet();
    }
}
