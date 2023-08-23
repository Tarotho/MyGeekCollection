package com.app.mgcback.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "videogame")
@Getter
@Setter
@NoArgsConstructor
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
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

    public Videogame(String name, String additionalInfo, String amountExact, String amountProducedEstimate, String brand, Boolean bundle, String color, Boolean limitedEdition, String platform, String regionCode, String releaseType, String releases, Boolean uniqueVariation) {
        this.name = name;
        this.additionalInfo = additionalInfo;
        this.amountExact = amountExact;
        this.amountProducedEstimate = amountProducedEstimate;
        this.brand = brand;
        this.bundle = bundle;
        this.color = color;
        this.limitedEdition = limitedEdition;
        this.platform = platform;
        this.regionCode = regionCode;
        this.releaseType = releaseType;
        this.releases = releases;
        this.uniqueVariation = uniqueVariation;
    }
}
