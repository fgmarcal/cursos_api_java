package com.cursos.rocket.api.modules.cursos.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;
import com.cursos.rocket.api.modules.cursos.repository.CoursesRepository;

@Service
public class UpdateCourseUseCase {
    
    @Autowired
    private CoursesRepository coursesRepository;

    public Optional<CoursesEntity> execute(CoursesEntity coursesEntity){
        var result = this.coursesRepository.findById(coursesEntity.getId());
        return result;
    }

    public CoursesEntity update(CoursesEntity coursesEntity){
        var result = this.coursesRepository.save(coursesEntity);
        return result;

    }
}
