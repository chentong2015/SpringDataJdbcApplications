package org.example.controller;

import jakarta.persistence.EntityManager;
import org.example.model.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntityManagerController {

    // TODO. 依赖注入容器中自动生成的EntityManager对象
    private final EntityManager entityManager;

    public EntityManagerController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/entity-manager")
    public ResponseEntity<List<UserEntity>> runByEntityManager() {
        String query = "select u from UserEntity u";
        List<UserEntity> userEntities = this.entityManager.createQuery(query, UserEntity.class).getResultList();
        System.out.println(userEntities.size());

        return ResponseEntity.ok().body(userEntities);
    }
}
