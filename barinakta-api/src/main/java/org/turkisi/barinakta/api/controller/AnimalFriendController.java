package org.turkisi.barinakta.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.turkisi.barinakta.api.error.EntityNotFoundException;
import org.turkisi.barinakta.api.model.*;
import org.turkisi.barinakta.api.repo.AnimalFriendRepository;
import org.turkisi.barinakta.api.repo.BreedRepository;
import org.turkisi.barinakta.api.repo.ShelterRepository;
import org.turkisi.barinakta.api.repo.SpeciesRepository;

import java.net.URI;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Controller
@RequestMapping("/pet")
public class AnimalFriendController {

    @Autowired
    private AnimalFriendRepository animalFriendRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private SpeciesRepository speciesRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @RequestMapping(path = "", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Void> savePet(AnimalFriend animalFriend, Long shelterId) throws EntityNotFoundException {

        Shelter shelter = shelterRepository.findOne(shelterId);
        if (shelter == null) {
            throw new EntityNotFoundException("No shelter with ID " + shelterId);
        }
        animalFriend.setShelter(shelter);
        AnimalFriend savedEntity = animalFriendRepository.save(animalFriend);

        return ResponseEntity.created(URI.create("/pet/" + savedEntity.getId())).build();
    }

    @RequestMapping(path = "", consumes = "application/json", method = RequestMethod.PUT)
    public
    @ResponseBody
    AnimalFriend updatePet(Long animalFriendId, AnimalFriend animalFriend, Long shelterId) throws EntityNotFoundException {

        Shelter shelter = shelterRepository.findOne(shelterId);
        if (shelter == null) {
            throw new EntityNotFoundException("No shelter with ID " + shelterId);
        }

        AnimalFriend dbEntity = animalFriendRepository.findOne(animalFriendId);
        if (dbEntity == null) {
            throw new EntityNotFoundException("No animal friend with ID " + animalFriendId);
        }
        dbEntity.setAge(animalFriend.getAge());
        dbEntity.setBio(animalFriend.getBio());
        dbEntity.setBreed(animalFriend.getBreed());
        dbEntity.setName(animalFriend.getName());
        dbEntity.setShelter(shelter);
        dbEntity.setPhoto(animalFriend.getPhoto());
        dbEntity.setStatus(animalFriend.getStatus());

        return animalFriendRepository.save(dbEntity);
    }

    @RequestMapping(path = "/{animalFriendId}", method = RequestMethod.GET)
    public AnimalFriend getPet(@PathVariable("animalFriendId") Long animalFriendId) {

        return animalFriendRepository.findOne(animalFriendId);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public Iterable<AnimalFriend> getAvailablePets() {

        return animalFriendRepository.findByStatus(AnimalFriendStatus.AVAILABLE);
    }

    @RequestMapping(path = "/{animalFriendId}", consumes = "application/json", method = RequestMethod.PATCH)
    public AnimalFriend setPetStatus(@PathVariable("animalFriendId") Long animalFriendId, @RequestBody AnimalFriendStatus status) throws EntityNotFoundException {

        AnimalFriend dbEntity = animalFriendRepository.findOne(animalFriendId);
        if (dbEntity == null) {
            throw new EntityNotFoundException("No animal friend with ID " + animalFriendId);
        }

        dbEntity.setStatus(status);
        return animalFriendRepository.save(dbEntity);
    }

    @RequestMapping(path = "/species", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Void> saveSpecies(Species species) {

        Species savedEntity = speciesRepository.save(species);

        return ResponseEntity.created(URI.create("/pet/species/" + savedEntity.getId())).build();
    }

    @RequestMapping(path = "/species/{speciesId}", consumes = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    Species updateSpecies(Long speciesId, Species species) throws EntityNotFoundException {

        Species dbEntity = speciesRepository.findOne(speciesId);
        if (dbEntity == null) {
            throw new EntityNotFoundException("No species with ID " + speciesId);
        }

        dbEntity.setName(species.getName());
        dbEntity.setBreeds(species.getBreeds());

        return speciesRepository.save(dbEntity);
    }

    @RequestMapping(path = "/species")
    public
    @ResponseBody
    Iterable<Species> getAllSpecies() {

        return speciesRepository.findAll();
    }
}
