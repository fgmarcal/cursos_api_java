package com.cursos.rocket.api.modules.cursos.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;
import com.cursos.rocket.api.modules.cursos.useCases.CreateCourseUseCase;
import com.cursos.rocket.api.modules.cursos.useCases.GetCoursesUseCase;
import com.cursos.rocket.api.modules.cursos.useCases.UpdateCourseUseCase;
import com.cursos.rocket.api.utils.Utils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class CoursesController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private GetCoursesUseCase getCoursesUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;
    
    @PostMapping("/courses")
    public ResponseEntity<Object> create(@Valid @RequestBody CoursesEntity courseEntity){
        try {
            var result = this.createCourseUseCase.execute(courseEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/courses")
    public List<CoursesEntity> getAll(){
            var result = this.getCoursesUseCase.getAll();
            return result;
    }
    @GetMapping("/courses/filter")
    public List<CoursesEntity> getByParam(
        @RequestParam(required = false) String name, 
        @RequestParam(required = false) String category)
        {
            return getCoursesUseCase.getByNameOrCategory(name, category);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Object> update(@RequestBody CoursesEntity courseEntity, @PathVariable UUID id) {
    
        var existingCourse = this.updateCourseUseCase.search(courseEntity).orElse(null);
    
        if (existingCourse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
        }
    
        Utils.copyNonNullProperties(courseEntity, existingCourse);
        var resultUpdated = this.updateCourseUseCase.update(existingCourse);
        return ResponseEntity.ok().body(resultUpdated);
    }
    
    
    


}
