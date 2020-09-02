package pl.sdacademy.jsonClassEntity.covid19.countries;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import pl.sdacademy.jsonClassEntity.Covid19;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryDataProvider implements Covid19<CountryApi> {
    private final List<CountryApi> countries = new ArrayList<>();

    public CountryDataProvider() {
        try {
            ObjectMapper mapper = configMapper();
            countries.addAll(Arrays.asList(mapper.readValue(getUri(), CountryApi[].class)));
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
    public List<CountryApi> getList() {
        return countries;
    }
}
