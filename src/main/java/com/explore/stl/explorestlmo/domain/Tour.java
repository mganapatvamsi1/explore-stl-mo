package com.explore.stl.explorestlmo.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Tour implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String blurb;

    @Column
    private Integer price;

    @Column
    private String duration;

    @Column(length = 2000)
    private String bullets;

    @ManyToOne
    private TourPackage tourPackage;

    @Column
    private Difficulty difficulty;

    @Column
    private Region region;

    @Autowired
    public Tour(String title, String description, String blurb,
                Integer price, String duration, String bullets,
                TourPackage tourPackage, Difficulty difficulty, Region region) {
        this.title = title;
        this.description = description;
        this.blurb = blurb;
        this.price = price;
        this.duration = duration;
        this.bullets = bullets;
        this.tourPackage = tourPackage;
        this.difficulty = difficulty;
        this.region = region;
    }

    // This forces anyone to construct fully initialized object
    protected Tour(){}

}
