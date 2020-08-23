package pl.sdacademy.jsonClasses;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class GetSummary {
    String uri = "https://api.covid19api.com/summary";
    FieldNamingPolicy naming = FieldNamingPolicy.UPPER_CAMEL_CASE;
    Type type = LocalDateTime.class;

    public Summary getSummary() {
        try {
            return getExampleApiWeb();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Summary getExampleApiWeb() throws IOException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(naming)
                .registerTypeAdapter(type, serializeDateTime())
                .create();
        URL url = new URL(uri);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return gson.fromJson(reader, Summary.class);
    }

    private JsonDeserializer<LocalDateTime> serializeDateTime() {
        return (json, type, jsonDeserializationContext)
                ->
                ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()
                ).toLocalDateTime();
    }

}
