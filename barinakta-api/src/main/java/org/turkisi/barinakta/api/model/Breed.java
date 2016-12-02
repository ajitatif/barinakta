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
}
