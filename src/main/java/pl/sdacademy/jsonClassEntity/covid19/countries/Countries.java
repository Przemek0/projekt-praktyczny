package pl.sdacademy.jsonClassEntity.covid19.countries;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import pl.sdacademy.jsonClassEntity.Covid19;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;

public class Countries implements Covid19 {
    private LocalDateTime time;
    private Country[] countries;

    private boolean steep(boolean refreshJson) {
        return refreshJson
                || time == null
                || Duration.between(time, LocalDateTime.now()).getSeconds() >= 60;
    }

    private void getJson() {
        System.out.println("Get");
        try {
            ObjectMapper mapper = JsonMapper.builder()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .build();
            countries = mapper.readValue(
                    new URL("https://api.covid19api.com/countries"),
                    Country[].class
            );
            time = LocalDateTime.now();
        } catch (IOException ignored) {
        }
    }

    @Override
    public JsonNode getTo(boolean refreshJson) {
        if (steep(refreshJson)) getJson();
        return new ObjectMapper().valueToTree(countries);
    }

    @Override
    public JsonNode getTo() {
        return getTo(false);
    }

    @Override
    public Country[] getCountries(boolean refreshJson) {
        if (steep(refreshJson)) getJson();
        return countries;
    }

    @Override
    public Country[] getCountries() {
        return getCountries(false);
    }
}
