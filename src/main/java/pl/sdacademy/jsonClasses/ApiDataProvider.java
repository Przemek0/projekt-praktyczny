package pl.sdacademy.jsonClasses;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class ApiDataProvider {
    private static final String uri = "https://api.covid19api.com/summary";
    private static final String catalog = "src/main/resources/pl/sdacademy/jsonClasses/";

    public static Summary apiDataProvider() {
        apiToFile("data.json", getApiFromUrl());
        return apiDataProvider("data.json");
    }

    private static Summary apiDataProvider(String fileName) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()
                )
                .create();
        try {
            return gson.fromJson(new FileReader(catalog + fileName), Summary.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void apiToFile(String fileName, StringBuilder stringApi) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(catalog + fileName));
            writer.append(stringApi);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String apiGetFile(){
        try {
            return Files.readString(Paths.get(catalog + "data.json"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static StringBuilder getApiFromUrl() {
        try {
            URL url = new URL(uri);
            BufferedReader reader;

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
            StringBuilder jsonString = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            reader.close();
            httpsURLConnection.disconnect();

            return jsonString;
        } catch (IOException e) {
            return null;
        }

    }

}
