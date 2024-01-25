package com.cursos.rocket.api.modules.cursos.useCases;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;
import com.cursos.rocket.api.modules.cursos.repository.CoursesRepository;

@Service
public class DeleteCourseUseCase {
    
    @Autowired
    private CoursesRepository coursesRepository;

    private Optional<CoursesEntity> search(@NonNull UUID id){
        var result = this.coursesRepository.findById(id);
        return result;
    }

    public void delete(@NonNull UUID id) throws NoSuchElementException{
        var courseExists = search(id);
        if(courseExists.isEmpty()){
            throw new NoSuchElementException();
        }
        coursesRepository.deleteById(id);
    }

}
