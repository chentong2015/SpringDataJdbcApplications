package org.example;

import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityManagerController {

    private EntityManager entityManager;

    @GetMapping("/entity-manager")
    public void runByEntityManager() {

    }
}
