package com.xplore.publisherapi.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SearchRequest {

    @Schema(title = "text to be searched", example = "Brazil")
    @NotBlank
    private String text;
}
