package pl.sdacademy.jsonClassEntity;

import com.fasterxml.jackson.databind.*;

import java.util.List;

public interface Covid19<T> {

    JsonNode getString();

    List<T> getList();

}
