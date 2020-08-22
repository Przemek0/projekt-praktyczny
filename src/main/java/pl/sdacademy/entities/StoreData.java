package pl.sdacademy.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class StoreData extends AbstractEntity{
    private LocalDateTime date;
    private int deaths;
    private int infections;
    private int recoveries;
    private int activeCases;
    private int totalDeaths;
    @ManyToOne
    private Country country;

    public StoreData() {
    }

    public StoreData(LocalDateTime date, int deaths, int infections, int recoveries, int activeCases, int totalDeaths, Country country) {
        this.date = date;
        this.deaths = deaths;
        this.infections = infections;
        this.recoveries = recoveries;
        this.activeCases = activeCases;
        this.totalDeaths = totalDeaths;
        this.country = country;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getInfections() {
        return infections;
    }

    public void setInfections(int infections) {
        this.infections = infections;
    }

    public int getRecoveries() {
        return recoveries;
    }

    public void setRecoveries(int recoveries) {
        this.recoveries = recoveries;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
