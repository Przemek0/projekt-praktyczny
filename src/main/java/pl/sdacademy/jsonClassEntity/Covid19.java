package pl.sdacademy.jsonClassEntity;

import com.fasterxml.jackson.databind.*;
import pl.sdacademy.jsonClassEntity.covid19.countries.CountryClass;

import java.util.List;

public interface Covid19<T> {

    JsonNode getString();

    List<T> getList();

}
