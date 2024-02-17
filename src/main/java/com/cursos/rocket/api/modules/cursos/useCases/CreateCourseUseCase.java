package com.cursos.rocket.api.modules.cursos.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursos.rocket.api.exceptions.CourseFoundException;
import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;
import com.cursos.rocket.api.modules.cursos.entities.valueObjects.CourseStatus;
import com.cursos.rocket.api.modules.cursos.repository.CoursesRepository;


@Service
public class CreateCourseUseCase {
    
    @Autowired
    private CoursesRepository coursesRepository;

    public CoursesEntity create(CoursesEntity coursesEntity){
        this.coursesRepository.findByName(coursesEntity.getName())
        .ifPresent((course) ->{
            throw new CourseFoundException();
        });
        coursesEntity.setCompletionStatus(CourseStatus.ACTIVE);
        return this.coursesRepository.save(coursesEntity);
    }
}
