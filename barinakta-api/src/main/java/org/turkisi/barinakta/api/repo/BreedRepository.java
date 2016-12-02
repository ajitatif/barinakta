package org.turkisi.barinakta.api.repo;

import org.springframework.data.repository.CrudRepository;
import org.turkisi.barinakta.api.model.Breed;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public interface BreedRepository extends CrudRepository<Breed, Long> {
}
