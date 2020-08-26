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
    private int numberResident;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<StoreData> storeData = new HashSet<>();

    public Country() {
    }

    public Country(Integer id, String name, String codeName, int numberResident) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
        this.numberResident = numberResident;
    }

    public Country(String name, String codeName, int numberResident) {
        this.name = name;
        this.codeName = codeName;
        this.numberResident = numberResident;
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

    public int getNumberResident() {
        return numberResident;
    }

    public void setNumberResident(int numberResident) {
        this.numberResident = numberResident;
    }

    public Set<StoreData> getStoreData() {
        return storeData;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", codeName='" + codeName + '\'' +
                ", numberResident=" + numberResident +
                ", storeData=" + storeData +
                ", id=" + id +
                '}';
    }
}
