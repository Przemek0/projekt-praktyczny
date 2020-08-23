package pl.sdacademy.jsonClasses;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Summary {
    private Global global;
    private Set<Countries> countries = new HashSet<>();
    private LocalDateTime date;

    public Summary() {
    }

    public Summary(Global global, Set<Countries> countries, LocalDateTime date) {
        this.global = global;
        this.countries = countries;
        this.date = date;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public Set<Countries> getCountries() {
        return countries;
    }

    public void setCountries(Set<Countries> countries) {
        this.countries = countries;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Covid19{" +
                "global=" + global +
                ", countries=" + countries +
                ", date=" + date +
                '}';
    }
}
