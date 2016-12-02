package org.turkisi.barinakta.api.model;

import javax.persistence.*;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
@Entity
@Table(name = "animal_friend")
public class AnimalFriend extends EntityBase {

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @ManyToOne
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @Column(name = "age")
    private Short age;

    @Column(name = "photo")
    @Lob
    private Byte[] photo;

    @Column(name = "bio")
    @Lob
    private String bio;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AnimalFriendStatus status;

    // TODO vaccines

    // TODO special trainings

    // TODO physical handicaps

    // TODO colors


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public AnimalFriendStatus getStatus() {
        return status;
    }

    public void setStatus(AnimalFriendStatus status) {
        this.status = status;
    }
}
