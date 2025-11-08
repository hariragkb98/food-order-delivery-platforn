package com.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// ✅ Do not start any web server or DB auto-config
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
                "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
        }
)
class ApplicationTests {

    @Test
    void contextLoads() {
        // ✅ Empty test just ensures Spring context starts
    }

}

