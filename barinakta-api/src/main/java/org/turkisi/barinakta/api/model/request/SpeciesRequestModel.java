package org.turkisi.barinakta.api.model.request;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class SpeciesRequestModel {

    private String name;
    private Long[] breedIds;

    public String getName() {
        return name;
    }

    public Long[] getBreedIds() {
        return breedIds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreedIds(Long[] breedIds) {
        this.breedIds = breedIds;
    }
}
