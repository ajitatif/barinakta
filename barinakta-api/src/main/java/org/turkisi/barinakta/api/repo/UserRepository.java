package org.turkisi.barinakta.api.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.turkisi.barinakta.api.model.User;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUserName(String username);
}
