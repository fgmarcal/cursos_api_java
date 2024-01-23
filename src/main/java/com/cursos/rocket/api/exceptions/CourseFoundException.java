package com.cursos.rocket.api.exceptions;

public class CourseFoundException extends RuntimeException{
    public CourseFoundException(){
        super("Curso já existe");
    }
}
