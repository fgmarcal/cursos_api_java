package com.cursos.rocket.api.modules.cursos.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;

public interface CoursesRepository extends JpaRepository<CoursesEntity, UUID> {
    List<CoursesEntity> findByNameOrCategory(String name, String category);
    
    Optional<CoursesEntity> findByName(String name);

    @NonNull
    Optional<CoursesEntity> findById(@NonNull UUID id);
}
