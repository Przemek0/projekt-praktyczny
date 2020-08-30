package pl.sdacademy.jsonClassEntity.covid19.countries;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.sdacademy.entities.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Country extends AbstractEntity {
    private String country;
    private String slug;
    private String is02;

    public Country() {
    }

    public Country(String country, String slug, String is02) {
        this.country = country;
        this.slug = slug;
        this.is02 = is02;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("Slug")
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @JsonProperty("IS02")
    public String getIs02() {
        return is02;
    }

    public void setIs02(String is02) {
        this.is02 = is02;
    }

    @Override
    public String toString() {
        return "Covid19_Country_Class{" +
                "country='" + country + '\'' +
                ", slug='" + slug + '\'' +
                ", is02='" + is02 + '\'' +
                '}';
    }

    /*  Example Response.
          {
                "Country"   : "Barbados",
                "Slug"      : "barbados",
                "ISO2"      : "BB"
          }
    */
}
