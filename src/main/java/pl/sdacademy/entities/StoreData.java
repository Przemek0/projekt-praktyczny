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

    public StoreData() {
    }

    public StoreData(LocalDateTime date, int deaths, int infections, int recoveries, int activeCases) {
        this.date = date;
        this.deaths = deaths;
        this.infections = infections;
        this.recoveries = recoveries;
        this.activeCases = activeCases;
    }

    public StoreData(int id, LocalDateTime date, int deaths, int infections, int recoveries, int activeCases) {
        this.id = id;
        this.date = date;
        this.deaths = deaths;
        this.infections = infections;
        this.recoveries = recoveries;
        this.activeCases = activeCases;
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

    @Override
    public String toString() {
        return "StoreData{" +
                "date=" + date +
                ", deaths=" + deaths +
                ", infections=" + infections +
                ", recoveries=" + recoveries +
                ", activeCases=" + activeCases +
                '}';
    }
}
