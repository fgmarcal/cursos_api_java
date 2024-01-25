package com.cursos.rocket.api.modules.cursos.useCases;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.cursos.rocket.api.modules.cursos.entities.valueObjects.CourseStatus;
import com.cursos.rocket.api.modules.cursos.repository.CoursesRepository;

@Service
public class CompleteCourseUseCase {
    
    @Autowired
    private CoursesRepository coursesRepository;

    public void complete (@NonNull UUID id) throws NoSuchElementException{
        var result = coursesRepository.findById(id).orElse(null);
        if(result == null){
            throw new NoSuchElementException();
        }
        result.setActive(CourseStatus.INACTIVE);
        coursesRepository.save(result);
    }
}
