package pl.sdacademy.jsonClassEntity.covid19.countries;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sdacademy.entities.AbstractEntity;

public class CountryData extends AbstractEntity {
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Slug")
    private String slug;
    @JsonProperty("ISO2")
    private String iso2;

    public CountryData() {
    }

    public CountryData(String country, String slug, String is02) {
        this.country = country;
        this.slug = slug;
        this.iso2 = is02;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @Override
    public String toString() {
        return "Covid19_Country_Class{" +
                "country='" + country + '\'' +
                ", slug='" + slug + '\'' +
                ", iso2='" + iso2 + '\'' +
                '}';
    }

}
