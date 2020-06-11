package com.explore.stl.explorestlmo.domain;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class TourRating {

    @EmbeddedId
    private TourRatingPk pk;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    public TourRating() {
    }

    /**
     * Create a fully initialized TourRating.
     *
     * @param pk      primiary key of a tour and customer id.
     * @param score   Integer score (1-5)
     * @param comment Optional comment from the customer
     */
    @Autowired
    public TourRating(TourRatingPk pk, Integer score, String comment) {
        this.pk = pk;
        this.score = score;
        this.comment = comment;
    }

}

