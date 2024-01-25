package com.cursos.rocket.api.modules.cursos.useCases;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;
import com.cursos.rocket.api.modules.cursos.repository.CoursesRepository;
import com.cursos.rocket.api.utils.Utils;

@Service
public class UpdateCourseUseCase {
    
    @Autowired
    private CoursesRepository coursesRepository;

    private Optional<CoursesEntity> search(CoursesEntity coursesEntity){
        var courseId = coursesEntity.getId();
        if(courseId != null){
            var result = this.coursesRepository.findById(courseId);
            return result;
        }
        return Optional.empty();
    }

    public CoursesEntity update(@NonNull CoursesEntity coursesEntity) throws NoSuchElementException{
        var existingCourse = search(coursesEntity).orElse(null);
        if(existingCourse == null){
            throw new NoSuchElementException();
        }
        
        Utils.copyNonNullProperties(coursesEntity, existingCourse);
        var result = this.coursesRepository.save(existingCourse);
        return result;

    }
}
