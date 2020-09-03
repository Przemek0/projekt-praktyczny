package pl.sdacademy.jsonClassEntity.covid19.countries;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryDataProvider {

    public List<CountryApi> getCountries() {
        List<CountryApi> countryApiList = new ArrayList<>();
        ObjectMapper mapper = configMapper();
        try {
            countryApiList = (Arrays.asList(mapper.readValue(getUri(), CountryApi[].class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryApiList;
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


}
