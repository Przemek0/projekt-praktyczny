package pl.sdacademy.jsonClassEntity.covid19.countryDayOne;

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

public class CountryDayOnes implements Covid19<CountryDayOneAll> {
    private final List<CountryDayOneAll> countryDayOne = new ArrayList<>();

    public CountryDayOnes(String slug) {
        try {
            ObjectMapper mapper = configMapper();
            countryDayOne.addAll(Arrays.asList(mapper.readValue(getUri(slug), CountryDayOneAll[].class)));
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

    private URL getUri(String slug) throws MalformedURLException {
        return new URL("https://api.covid19api.com/total/dayone/country/" + slug);
    }

    @Override
    public JsonNode getString() {
        return new ObjectMapper().valueToTree(countryDayOne);
    }

    @Override
    public List<CountryDayOneAll> getList() {
        return countryDayOne;
    }

}
