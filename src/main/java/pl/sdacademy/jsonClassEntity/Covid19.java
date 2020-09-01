package pl.sdacademy.jsonClassEntity;

import com.fasterxml.jackson.databind.*;
import pl.sdacademy.jsonClassEntity.covid19.countries.CountryData;
import java.time.LocalDateTime;
import java.util.List;

public interface Covid19 {

    JsonNode getString();

   List<CountryData> getCountries();

}
