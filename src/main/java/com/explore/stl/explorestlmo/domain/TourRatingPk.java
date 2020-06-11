package com.explore.stl.explorestlmo.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Tour Rating Primary Key containing a Tour and a Customer Identifier
 * Created by Manikanta Ganapathiraju
 */
@Embeddable
@Data
public class TourRatingPk implements Serializable {

    @ManyToOne
    private Tour tour;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer customerId;

    public TourRatingPk(){
    }

    /**
     * Fully initialize a Tour Rating Pk
     *
     * @param tour          the tour.
     * @param customerId    the customer identifier.
     */
    @Autowired
    public TourRatingPk(Tour tour, Integer customerId) {
        this.tour = tour;
        this.customerId = customerId;
    }

}

