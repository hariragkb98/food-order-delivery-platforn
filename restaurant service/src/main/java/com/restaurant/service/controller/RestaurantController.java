package com.restaurant.service.controller;

import com.restaurant.service.model.MenuItem;
import com.restaurant.service.model.Restaurant;
import com.restaurant.service.repository.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final RestaurantRepository repo;
    public RestaurantController(RestaurantRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Restaurant> list() { return repo.findAll(); }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant r) { return repo.save(r); }

    @PostMapping("/{id}/menu")
    public Restaurant addMenuItem(@PathVariable Long id, @RequestBody MenuItem item) {
        Restaurant r = repo.findById(id).orElseThrow();
        r.getMenu().add(item);
        return repo.save(r);
    }
}
