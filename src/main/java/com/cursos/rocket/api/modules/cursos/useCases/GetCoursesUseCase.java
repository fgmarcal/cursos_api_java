package com.cursos.rocket.api.modules.cursos.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;
import com.cursos.rocket.api.modules.cursos.repository.CoursesRepository;

@Service
public class GetCoursesUseCase {
    
    @Autowired
    private CoursesRepository coursesRepository; 

    public List<CoursesEntity> getAll(){
        return this.coursesRepository.findAll();
    }

    public List<CoursesEntity> getByNameOrCategory(String name, String category){
        return this.coursesRepository.findByNameOrCategory(name, category);
    }

    

}
