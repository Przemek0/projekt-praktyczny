package pl.sdacademy.jsonClassEntity;

import com.fasterxml.jackson.databind.*;
import pl.sdacademy.jsonClassEntity.covid19.countries.Country;

public interface Covid19 {

    JsonNode getTo(boolean refreshJson);

    JsonNode getTo();

    Country[] getCountries(boolean refreshJson);

    Country[] getCountries();

}
