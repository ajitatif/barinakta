package org.turkisi.barinakta.api.model;

import org.springframework.data.rest.core.config.Projection;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public interface Projections {

    @Projection(name = "animal_friend", types = AnimalFriend.class)
    interface AnimalFriendProjection {

        Long getId();

        String getName();

        ShelterProjection getShelter();

        BreedProjection getBreed();

        Short getAge();

        Byte[] getPhoto();

        String getBio();

        AnimalFriendStatus getStatus();

    }

    @Projection(name = "breed", types = Breed.class)
    interface BreedProjection {

        Long getId();
        String getName();
        SpeciesProjection getSpecies();
    }

    @Projection(name = "species", types = Species.class)
    interface SpeciesProjection {

        Long getId();
        String getName();
    }

    @Projection(name = "shelter", types = Shelter.class)
    interface ShelterProjection {

        Long getId();

        String getName();

        String getPhone();

        String getGisCoordinates();

        Byte[] getPhoto();
    }
}
