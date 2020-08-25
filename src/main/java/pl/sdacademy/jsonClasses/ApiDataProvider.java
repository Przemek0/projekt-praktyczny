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

    public static Summary apiDataProvider() {
        String nameFile = "data.json";
        apiToFile(nameFile, getApiFromUrl());
        return apiDataProvider(nameFile);
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
            return gson.fromJson(new FileReader("src/main/resources/pl/sdacademy/jsonClasses/" + fileName), Summary.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void apiToFile(String fileName, String stringApi) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/pl/sdacademy/jsonClasses/" + fileName));
            writer.append(stringApi);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getApiFromUrl() {
        try {
            URL url = new URL("https://api.covid19api.com/summary");
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

            return jsonString.toString();
        } catch (IOException e) {
            return null;
        }
    }

}
