package org.turkisi.barinakta.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.turkisi.barinakta.api.error.EntityNotFoundException;
import org.turkisi.barinakta.api.model.*;
import org.turkisi.barinakta.api.model.request.AnimalFriendRequestModel;
import org.turkisi.barinakta.api.model.request.BreedRequestModel;
import org.turkisi.barinakta.api.model.request.SpeciesRequestModel;
import org.turkisi.barinakta.api.repo.AnimalFriendRepository;
import org.turkisi.barinakta.api.repo.BreedRepository;
import org.turkisi.barinakta.api.repo.ShelterRepository;
import org.turkisi.barinakta.api.repo.SpeciesRepository;

import java.net.URI;
import java.util.Arrays;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Controller
@RequestMapping("/pet")
public class AnimalFriendController {

    private AnimalFriendRepository animalFriendRepository;

    private BreedRepository breedRepository;

    private SpeciesRepository speciesRepository;

    private ShelterRepository shelterRepository;

    public AnimalFriendController(AnimalFriendRepository animalFriendRepository, BreedRepository breedRepository,
                                  SpeciesRepository speciesRepository, ShelterRepository shelterRepository) {

        this.animalFriendRepository = animalFriendRepository;
        this.breedRepository = breedRepository;
        this.speciesRepository = speciesRepository;
        this.shelterRepository = shelterRepository;
    }

}
