package org.turkisi.barinakta.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.turkisi.barinakta.api.error.EntityNotFoundException;
import org.turkisi.barinakta.api.model.Shelter;
import org.turkisi.barinakta.api.repo.ShelterRepository;

import java.net.URI;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Controller
@RequestMapping("/shelter")
public class ShelterController {

    private ShelterRepository shelterRepository;

    public ShelterController(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Void> saveShelter(@RequestBody Shelter shelter) {

        Shelter savedShelter = shelterRepository.save(shelter);

        return ResponseEntity.created(URI.create("/shelter/" + savedShelter.getId())).build();
    }

    @RequestMapping(value = "/{shelterId}", method = RequestMethod.PUT, consumes = "application/json")
    public @ResponseBody Shelter updateShelter(@PathVariable("shelterId") Long shelterId, @RequestBody Shelter shelter)
            throws EntityNotFoundException {

        Shelter shelterOnDb = shelterRepository.findOne(shelterId);
        if (shelterOnDb == null) {
            throw new EntityNotFoundException("No shelter with ID " + shelterId + " is found");
        }

        shelterOnDb.setName(shelter.getName());
        shelterOnDb.setAddress(shelter.getAddress());
        shelterOnDb.setGisCoordinates(shelter.getGisCoordinates());
        shelterOnDb.setPhone(shelter.getPhone());

        shelterRepository.save(shelterOnDb);

        return shelterOnDb;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody Iterable<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @RequestMapping(value = "/{shelterId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Shelter getShelter(@PathVariable("shelterId") Long shelterId) throws EntityNotFoundException {
        Shelter shelter = shelterRepository.findOne(shelterId);

        if (shelter == null) {
            throw new EntityNotFoundException("No shelter with ID " + shelterId);
        }

        return shelter;
    }
}
