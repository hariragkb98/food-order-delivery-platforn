package com.restaurant.service.model;
import jakarta.persistence.*;
import java.util.*;




    @Entity
    public class Restaurant {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String address;

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<MenuItem> menu = new ArrayList<>();

        public List<MenuItem> getMenu() {
            return menu;
        }

        public void setMenu(List<MenuItem> menu) {
            this.menu = menu;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

// getters & setters
    }

