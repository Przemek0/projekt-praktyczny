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

    public static Summary getSummary() {
        try {
            return getExampleApiWeb();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Summary getExampleApiWeb() throws IOException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()
                )
                .create();
        String uri = "https://api.covid19api.com/summary";
        URL url = new URL(uri);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return gson.fromJson(reader, Summary.class);
    }

}
