package org.turkisi.barinakta.api.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.turkisi.barinakta.api.model.AnimalFriend;
import org.turkisi.barinakta.api.model.AnimalFriendStatus;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@RepositoryRestResource
public interface AnimalFriendRepository extends CrudRepository<AnimalFriend, Long> {

    Iterable<AnimalFriend> findByStatus(AnimalFriendStatus status);
}
