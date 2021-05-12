package com.trustly.challenger.github.presentation.controller.representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorRepresentation {

    private String title;
    private Integer status;
    private String details;

}