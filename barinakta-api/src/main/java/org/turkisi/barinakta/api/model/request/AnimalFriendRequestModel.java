package org.turkisi.barinakta.api.model.request;

import org.turkisi.barinakta.api.model.AnimalFriend;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class AnimalFriendRequestModel {

    private AnimalFriend animalFriend;
    private Long shelterId;

    public AnimalFriend getAnimalFriend() {
        return animalFriend;
    }

    public void setAnimalFriend(AnimalFriend animalFriend) {
        this.animalFriend = animalFriend;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }
}
