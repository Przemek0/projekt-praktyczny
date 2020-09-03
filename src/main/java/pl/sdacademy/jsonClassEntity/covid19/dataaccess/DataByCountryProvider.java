package pl.sdacademy.jsonClassEntity.covid19.dataaccess;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class DataByCountryProvider {

    public Set<DataApi> getData(String slug) {
        List<DataApi> dataApiList = new ArrayList<>();
        ObjectMapper mapper = configMapper();
        try {
            dataApiList = Arrays.asList(mapper.readValue(getUri(slug), DataApi[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashSet<>(dataApiList);
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
}
