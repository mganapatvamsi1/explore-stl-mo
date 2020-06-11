package com.explore.stl.explorestlmo.services;

import com.explore.stl.explorestlmo.domain.Difficulty;
import com.explore.stl.explorestlmo.domain.Region;
import com.explore.stl.explorestlmo.domain.Tour;
import com.explore.stl.explorestlmo.domain.TourPackage;
import com.explore.stl.explorestlmo.repo.TourPackageRepository;
import com.explore.stl.explorestlmo.repo.TourRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String title, String description,
                           Integer price,String blurb,
                           String duration, String bullets,
                           String keywords, String tourPackageCode,
                           Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findById(tourPackageCode).orElse(null);

        return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets,
                tourPackage, difficulty, region));
    }

    public Iterable<Tour> lookUp() { return tourRepository.findAll(); }

    public long count() { return tourRepository.count(); }

}
