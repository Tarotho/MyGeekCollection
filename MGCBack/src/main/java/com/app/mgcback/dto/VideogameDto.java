package com.app.mgcback.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideogameDto {

    @NotBlank
    private String name;
    private String additionalInfo;
    private String amountExact;
    private String amountProducedEstimate;
    private String brand;
    private Boolean bundle;
    private String color;
    private Boolean limitedEdition;
    private String platform;
    private String regionCode;
    private String releaseType;
    private String releases;
    private Boolean uniqueVariation;
}
