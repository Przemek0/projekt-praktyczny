package pl.sdacademy.jsonClassEntity.covid19.countries;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryApi {
    @JsonProperty("Country")
    private String name;
    @JsonProperty("Slug")
    private String slug;
    @JsonProperty("ISO2")
    private String codeName;

    public CountryApi() {
    }

    public CountryApi(String name, String slug, String iso2) {
        this.name = name;
        this.slug = slug;
        this.codeName = iso2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public String toString() {
        return "Covid19_Country_Class{" +
                "country='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", codeName='" + codeName + '\'' +
                '}';
    }

}
