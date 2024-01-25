package com.cursos.rocket.api.modules.cursos.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.rocket.api.modules.cursos.entities.CoursesEntity;
import com.cursos.rocket.api.modules.cursos.useCases.CompleteCourseUseCase;
import com.cursos.rocket.api.modules.cursos.useCases.CreateCourseUseCase;
import com.cursos.rocket.api.modules.cursos.useCases.DeleteCourseUseCase;
import com.cursos.rocket.api.modules.cursos.useCases.GetCoursesUseCase;
import com.cursos.rocket.api.modules.cursos.useCases.UpdateCourseUseCase;
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

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @Autowired
    private CompleteCourseUseCase completeCourseUseCase;
    
    @PostMapping("/courses")
    public ResponseEntity<Object> create(@Valid @RequestBody CoursesEntity courseEntity){
        try {
            var result = this.createCourseUseCase.create(courseEntity);
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
    public ResponseEntity<Object> update(@RequestBody CoursesEntity courseEntity, @NonNull @PathVariable UUID id) {
        try {
            courseEntity.setId(id);
            var resultUpdated = this.updateCourseUseCase.update(courseEntity);
            return ResponseEntity.ok().body(resultUpdated);
    
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage().toString());
        }
    
    }

    @DeleteMapping("/courses/{id}")
    public void delete(@PathVariable @NonNull UUID id){
        try {
            deleteCourseUseCase.delete(id);
        } catch (NoSuchElementException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
        } catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage().toString());
        }
    }

    @PatchMapping("/courses/{id}/complete")
    public void complete(@PathVariable @NonNull UUID id){
        try{
            completeCourseUseCase.complete(id);
        }
        catch (NoSuchElementException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não encontrado");
        } catch (Exception e){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage().toString());
        }
    }

}
