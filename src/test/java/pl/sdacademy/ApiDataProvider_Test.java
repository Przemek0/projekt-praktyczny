package pl.sdacademy;

import com.google.gson.*;
import org.junit.Test;
import pl.sdacademy.jsonClasses.ApiDataProvider;
import pl.sdacademy.jsonClasses.Summary;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import static org.junit.Assert.assertEquals;

public class ApiDataProvider_Test {

    @Test
    public void test() {
        //given
        Summary summary = ApiDataProvider.apiDataProvider();
        //when
        String text = getString(summary);
        //then
        assertEquals("Test", getString(getSummary(text)), text);
    }

    private static String getString(Summary summary){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime
                                .parse(json.getAsString()))
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>) (date, type,
                                                         jsonSerializationContext) -> new JsonPrimitive(date.toString()+'Z'))
                .create();
        return gson.toJson(summary);
    }

    private static Summary getSummary(String text){
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .registerTypeAdapter(
                        LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                                ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime()
                )
                .create();
        return gson.fromJson(text, Summary.class);
    }

}
