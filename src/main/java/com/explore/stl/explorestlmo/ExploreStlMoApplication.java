package com.explore.stl.explorestlmo;

import com.explore.stl.explorestlmo.domain.Difficulty;
import com.explore.stl.explorestlmo.domain.Region;
import com.explore.stl.explorestlmo.services.TourPackageService;
import com.explore.stl.explorestlmo.services.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class ExploreStlMoApplication implements CommandLineRunner {

    @Autowired
    private TourService tourService;

    @Autowired
    private TourPackageService tourPackageService;

    static class TourFromFile {
        //attributes as listed in the .json file
        private String packageType, title, description, blurb, price, length, bullets, keywords,  difficulty, region;

        /**
         * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile Object.
         *
         * @return a List of TourFromFile objects.
         * @throws IOException if ObjectMapper unable to open file.
         */
        static List<TourFromFile> importTours() throws IOException {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                    readValue(TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"),new TypeReference<List<TourFromFile>>(){});
        }
    }

    @Override
    public void run(String... args) throws Exception {
        //Create the default tour packages
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");

        tourPackageService.lookup().forEach(tourPackage -> {
            System.out.println(tourPackage.getCode() + " " + tourPackage.getName());
        });

        //Persist the Tours to the database
        TourFromFile.importTours().forEach(tourFromFile -> tourService.createTour(
                tourFromFile.title,
                tourFromFile.description,
                Integer.parseInt(tourFromFile.price),
                tourFromFile.blurb,
                tourFromFile.length,
                tourFromFile.bullets,
                tourFromFile.keywords,
                tourFromFile.packageType,
                Difficulty.valueOf(tourFromFile.difficulty),
                Region.findByLabel(tourFromFile.region)
        ));


        System.out.println("Number of tours =" + tourService.count());
    }

    /**
     * Helper class to import the records in the ExploreCalifornia.json
     * */
    public static void main(String[] args) {
        SpringApplication.run(ExploreStlMoApplication.class, args);
    }


}
