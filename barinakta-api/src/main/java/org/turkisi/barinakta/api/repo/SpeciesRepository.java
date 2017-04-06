package org.turkisi.barinakta.api.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.turkisi.barinakta.api.model.Projections;
import org.turkisi.barinakta.api.model.Species;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@RepositoryRestResource(excerptProjection = Projections.SpeciesProjection.class)
public interface SpeciesRepository extends CrudRepository<Species, Long> {
}
