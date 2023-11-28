package com.mygeekcollection.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "collection")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "videogame_id")
    private Videogame videogame;

    @Column(name = "general_condition")
    private String generalCondition;

    @Column(name = "box")
    private Boolean box;

    @Column(name = "manual")
    private Boolean manual;

}
