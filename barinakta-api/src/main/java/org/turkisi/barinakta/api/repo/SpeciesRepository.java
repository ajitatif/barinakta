package org.turkisi.barinakta.api.repo;

import org.springframework.data.repository.CrudRepository;
import org.turkisi.barinakta.api.model.Species;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public interface SpeciesRepository extends CrudRepository<Species, Long> {
}
