package pl.sdacademy.jsonClasses;

import pl.sdacademy.entities.Country;
import java.util.List;

public class ApiEntityDataProvider implements EntityDataProvider {
    @Override
    public List<Country> load() {
        return ApiObjectToEntityMapper.map(ApiDataProvider.apiDataProvider());
    }

}
