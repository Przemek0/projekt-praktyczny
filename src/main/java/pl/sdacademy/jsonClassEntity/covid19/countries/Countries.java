package pl.sdacademy.jsonClassEntity.covid19.countries;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import pl.sdacademy.jsonClassEntity.Covid19;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Countries implements Covid19 {
    private List<CountryData> countries = new ArrayList<>();

    public Countries() {
        try {
            ObjectMapper mapper = configMapper();
            countries.addAll(Arrays.asList(mapper.readValue(getUri(), CountryData[].class)));
        } catch (IOException ignored) {
        }
    }

    private JsonMapper configMapper() {
        return JsonMapper.builder()
                .configure(getParameter(), false)
                .build();
    }

    private DeserializationFeature getParameter() {
        return DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
    }

    private URL getUri() throws MalformedURLException {
        return new URL("https://api.covid19api.com/countries");
    }

    @Override
    public JsonNode getString() {
        return new ObjectMapper().valueToTree(countries);
    }

    @Override
    public List<CountryData> getCountries() {
        return countries;
    }
}
