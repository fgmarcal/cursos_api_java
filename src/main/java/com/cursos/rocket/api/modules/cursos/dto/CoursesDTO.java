package com.cursos.rocket.api.modules.cursos.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursesDTO {
    
    private String name;
    private String category;
    private UUID id;
    boolean active;
}
