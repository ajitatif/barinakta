package org.turkisi.barinakta.api.repo;

import org.springframework.data.repository.CrudRepository;
import org.turkisi.barinakta.api.model.Shelter;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public interface ShelterRepository extends CrudRepository<Shelter, Long> {
}
