package com.cursos.rocket.api.modules.cursos.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;

public interface CoursesRepository extends JpaRepository<CoursesEntity, UUID> {
    Optional<CoursesEntity> findByNameOrCategory(String name, String category);
    Optional<CoursesEntity> findByName(String name);
}
