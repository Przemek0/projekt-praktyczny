package pl.sdacademy.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Country extends AbstractEntity{
    private String name;
    private String codeName;
    private String slug;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private final Set<StoreData> storeData = new HashSet<>();

    public Country() {
    }

    public Country(Integer id, String name, String codeName, String slug) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.slug = slug;
    }

    public Country(String name, String codeName, String slug) {
        this.name = name;
        this.codeName = codeName;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Set<StoreData> getStoreData() {
        return storeData;
    }
}
