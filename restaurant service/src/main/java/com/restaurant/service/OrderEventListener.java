package com.restaurant.service;

import com.order.service.events.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventListener {
    @KafkaListener(topics = "order-events", groupId = "restaurant-service-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderEvent(OrderEvent event) {
        System.out.println("🍽️ Received order event: " + event);
        // business logic: validate restaurantId, check items availability, optionally update DB
        // e.g., auto-accept: save acceptance and possibly publish response event
    }
}
