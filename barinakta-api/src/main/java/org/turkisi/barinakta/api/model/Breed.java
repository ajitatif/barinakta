package org.turkisi.barinakta.api.model;

import javax.persistence.*;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Entity
@Table(name = "breed")
public class Breed extends EntityBase {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
