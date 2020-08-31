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

public class Countries implements Covid19 {
    private LocalDateTime time;
    private Country[] countries;

    public Countries() {
        System.out.println("Get");
        try {
            ObjectMapper mapper = configMapper();
            countries = mapper.readValue(getUri(), Country[].class);
            time = LocalDateTime.now();
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
    public LocalDateTime getTimeLastRefresh() {
        return time;
    }

    @Override
    public JsonNode getString() {
        return new ObjectMapper().valueToTree(countries);
    }

    @Override
    public Country[] getCountries() {
        return countries;
    }
}
