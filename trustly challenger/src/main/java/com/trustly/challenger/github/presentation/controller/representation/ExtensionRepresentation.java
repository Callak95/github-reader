package com.trustly.challenger.github.presentation.controller.representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExtensionRepresentation {

    private String extension;
    private Long totalLines;
    private Long totalBytes;
}
