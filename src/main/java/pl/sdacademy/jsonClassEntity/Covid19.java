package pl.sdacademy.jsonClassEntity;

import com.fasterxml.jackson.databind.*;
import pl.sdacademy.jsonClassEntity.covid19.countries.Country;

import java.time.LocalDateTime;

public interface Covid19 {

    LocalDateTime getTimeLastRefresh();

    JsonNode getString();

    Country[] getCountries();

}
