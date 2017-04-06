package org.turkisi.barinakta.api.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.turkisi.barinakta.api.model.Breed;
import org.turkisi.barinakta.api.model.Projections;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@RepositoryRestResource(excerptProjection = Projections.BreedProjection.class)
public interface BreedRepository extends CrudRepository<Breed, Long> {
}
