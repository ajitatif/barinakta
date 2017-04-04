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

    @RequestMapping(path = "", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Void> savePet(@RequestBody AnimalFriendRequestModel animalFriend) throws EntityNotFoundException {

        Shelter shelter = shelterRepository.findOne(animalFriend.getShelterId());
        if (shelter == null) {
            throw new EntityNotFoundException("No shelter with ID " + animalFriend.getShelterId());
        }
        animalFriend.getAnimalFriend().setShelter(shelter);
        AnimalFriend savedEntity = animalFriendRepository.save(animalFriend.getAnimalFriend());

        return ResponseEntity.created(URI.create("/pet/" + savedEntity.getId())).build();
    }

    @RequestMapping(path = "/{animalFriendId}", consumes = "application/json", method = RequestMethod.PUT)
    public
    @ResponseBody
    AnimalFriend updatePet(@PathVariable("animalFriendId") Long animalFriendId, @RequestBody AnimalFriendRequestModel animalFriendRequestModel)
            throws EntityNotFoundException {

        AnimalFriend animalFriend = animalFriendRequestModel.getAnimalFriend();
        Long shelterId = animalFriendRequestModel.getShelterId();
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
    public @ResponseBody AnimalFriend getPet(@PathVariable("animalFriendId") Long animalFriendId) {

        return animalFriendRepository.findOne(animalFriendId);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public @ResponseBody Iterable<AnimalFriend> getAvailablePets() {

        return animalFriendRepository.findByStatus(AnimalFriendStatus.LOOKING_FOR_HOME);
    }

    @RequestMapping(path = "/{animalFriendId}", consumes = "application/json", method = RequestMethod.PATCH)
    public @ResponseBody AnimalFriend setPetStatus(@PathVariable("animalFriendId") Long animalFriendId,
                                                   @RequestBody AnimalFriendStatus status) throws EntityNotFoundException {

        AnimalFriend dbEntity = animalFriendRepository.findOne(animalFriendId);
        if (dbEntity == null) {
            throw new EntityNotFoundException("No animal friend with ID " + animalFriendId);
        }

        dbEntity.setStatus(status);
        return animalFriendRepository.save(dbEntity);
    }

    @RequestMapping(path = "/species", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Void> saveSpecies(@RequestBody SpeciesRequestModel species) throws EntityNotFoundException {

        Species entity = new Species();
        entity.setName(species.getName());

        Iterable<Breed> breeds = breedRepository.findAll(Arrays.asList(species.getBreedIds()));

        entity.setName(species.getName());
        entity.getBreeds().clear();
        breeds.forEach(entity.getBreeds()::add);

        if (entity.getBreeds().size() != species.getBreedIds().length) {
            throw new EntityNotFoundException("At least one of the breeds is not found on DB");
        }

        Species savedEntity = speciesRepository.save(entity);

        return ResponseEntity.created(URI.create("/pet/species/" + savedEntity.getId())).build();
    }

    @RequestMapping(path = "/species/{speciesId}", consumes = "application/json", method = RequestMethod.POST)
    public
    @ResponseBody
    Species updateSpecies(@PathVariable("speciesId") Long speciesId, @RequestBody SpeciesRequestModel species) throws EntityNotFoundException {

        Species dbEntity = speciesRepository.findOne(speciesId);
        if (dbEntity == null) {
            throw new EntityNotFoundException("No species with ID " + speciesId);
        }

        Iterable<Breed> breeds = breedRepository.findAll(Arrays.asList(species.getBreedIds()));

        dbEntity.setName(species.getName());
        dbEntity.getBreeds().clear();
        breeds.forEach(dbEntity.getBreeds()::add);

        if (dbEntity.getBreeds().size() != species.getBreedIds().length) {
            throw new EntityNotFoundException("At least one of the breeds is not found on DB");
        }

        return speciesRepository.save(dbEntity);
    }

    @RequestMapping(path = "/species")
    public
    @ResponseBody
    Iterable<Species> getAllSpecies() {

        return speciesRepository.findAll();
    }

    @RequestMapping(path = "/breed", method = RequestMethod.GET)
    public @ResponseBody Iterable<Breed> getAllBreeds() {

        return breedRepository.findAll();
    }

    @RequestMapping(path = "/breed/{breedId}", method = RequestMethod.GET)
    public @ResponseBody Breed getBreed(@PathVariable("breedId") Long breedId) {

        return breedRepository.findOne(breedId);
    }

    @RequestMapping(path = "/breed", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Void> saveBreed(@RequestBody BreedRequestModel breed) throws EntityNotFoundException {

        Breed entity = new Breed();
        entity.setName(breed.getName());

        Species species = speciesRepository.findOne(breed.getSpeciesId());
        if (species == null) {
            throw new EntityNotFoundException("No species with id " + breed.getSpeciesId());
        }

        entity.setSpecies(species);
        Breed savedBreed = breedRepository.save(entity);

        return ResponseEntity.created(URI.create("/breed/" + savedBreed.getId())).build();
    }

    @RequestMapping(path = "/breed/{breedId}")
    public
    @ResponseBody
    Breed updateBreed(@PathVariable("breedId") Long breedId, @RequestBody BreedRequestModel breed) throws EntityNotFoundException {

        Breed breedOnDb = breedRepository.findOne(breedId);
        if (breedOnDb == null) {
            throw new EntityNotFoundException("No breed with ID " + breedId);
        }

        Species species = speciesRepository.findOne(breed.getSpeciesId());

        if (species == null) {
            throw new EntityNotFoundException("No species with ID " + breed.getSpeciesId());
        }

        breedOnDb.setName(breed.getName());
        breedOnDb.setSpecies(species);

        breedRepository.save(breedOnDb);

        return breedOnDb;
    }
}
