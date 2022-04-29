package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

public class ApartmentService{

    private static final Logger LOGGER = LogManager.getLogger(ApartmentService.class);
    private static final String PATH_TO_FILE = "AccountingSystem/university-lab/src/main/resources/results.txt";

    private ApartmentService() {

    }

    private static class SingletonHolder {
        private static final ApartmentService APARTMENT_SERVICE = new ApartmentService();
    }

    public static ApartmentService getInstance() {
        return SingletonHolder.APARTMENT_SERVICE;
    }

    public double getTotalApartmentArea(Apartment apartment) {

        final double area = apartment.getTotalApartmentLength() * apartment.getTotalApartmentWidth();
        try {
            FileWriter outputStream = new FileWriter(PATH_TO_FILE, true);
            outputStream.write(String.valueOf(area) + '\n');
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return area;
    }
    
    public void compareInfo(Apartment firstApartment, Apartment secondApartment){
        if (firstApartment == null || secondApartment == null) {
            throw new NullPointerException("Apartment must be a not null value");
        }

        LOGGER.info("\nCompare the {}(apartment id) with {}(apartment id) in terms of parameters",
                firstApartment.getId(), secondApartment.getId());

        if (firstApartment.getTotalApartmentLength() > secondApartment.getTotalApartmentLength()) {
            LOGGER.info("Apartment {} longer than apartment {}", firstApartment.getId(), secondApartment.getId());
        } else if (firstApartment.getTotalApartmentLength() == secondApartment.getTotalApartmentLength()) {
            LOGGER.info("The length of the apartments are equal");
        } else {
            LOGGER.info("apartment {} less in length than apartment {}", firstApartment.getId(), secondApartment.getId());
        }

        if (firstApartment.getTotalApartmentWidth() > secondApartment.getTotalApartmentWidth()) {
            LOGGER.info("Apartment {} wider than apartment {}", firstApartment.getId(), secondApartment.getId());
        } else if (firstApartment.getTotalApartmentWidth() == firstApartment.getTotalApartmentWidth()) {
            LOGGER.info("The width of the apartments are equal");
        } else {
            LOGGER.info("Apartment {} less in width than apartment {}", firstApartment.getId(), secondApartment.getId());
        }

        if (firstApartment.getNumberOfResidents() > secondApartment.getNumberOfResidents()) {
            LOGGER.info("The {} apartment has more residents that the {} apartment", firstApartment.getId(), secondApartment.getId());
        } else if (firstApartment.getNumberOfResidents() == secondApartment.getNumberOfResidents()) {
            LOGGER.info("The number of people living in the apartment is the same");
        } else {
            LOGGER.info("The number of people living in the {} apartment is less than in the {} apartment", firstApartment.getId(), secondApartment.getId());
        }
    }
}
