package com.order.service.controller;

import com.order.service.events.OrderEvent;
import com.order.service.model.OrderEntity;
import com.order.service.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import org.springframework.kafka.core.KafkaTemplate;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repo;
    private final KafkaTemplate<String, Object> kafka;

    public OrderController(OrderRepository repo, KafkaTemplate<String, Object> kafka) {
        this.repo = repo;
        this.kafka = kafka;
    }

    @PostMapping
    public OrderEntity create(@RequestBody OrderEntity order) {
        order.setStatus("PENDING");
        OrderEntity saved = repo.save(order);
        // publish event
        OrderEvent ev = new OrderEvent();
        ev.setOrderId(saved.getId());
        ev.setRestaurantId(saved.getRestaurantId());
        ev.setCustomerId(saved.getCustomerId());
        ev.setItems(saved.getItems());
        ev.setTotal(saved.getTotal());
        kafka.send("order-events", ev);
        return saved;
    }

    @GetMapping("/{id}")
    public OrderEntity get(@PathVariable Long id) { return repo.findById(id).orElseThrow(); }

    @PutMapping("/{id}/status")
    public OrderEntity updateStatus(@PathVariable Long id, @RequestParam String status) {
        OrderEntity o = repo.findById(id).orElseThrow();
        o.setStatus(status);
        return repo.save(o);
    }
}