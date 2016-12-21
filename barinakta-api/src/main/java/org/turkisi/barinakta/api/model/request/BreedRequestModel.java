package org.turkisi.barinakta.api.model.request;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class BreedRequestModel {

    private String name;
    private Long speciesId;

    public String getName() {
        return name;
    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }
}
