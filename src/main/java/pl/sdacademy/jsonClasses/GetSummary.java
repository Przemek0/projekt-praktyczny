package pl.sdacademy.jsonClasses;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import java.io.*;
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

    public static Summary getExampleApiWeb() throws IOException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(LocalDateTime.class, serializeDateTime())
                .create();
        URL url = new URL("https://api.covid19api.com/summary");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return gson.fromJson(reader, Summary.class);
    }


    private static JsonDeserializer<LocalDateTime> serializeDateTime() {
        return (json, type, jsonDeserializationContext)
                ->
                ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString())
                        .toLocalDateTime();
    }

}
