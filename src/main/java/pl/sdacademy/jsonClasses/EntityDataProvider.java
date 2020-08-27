package pl.sdacademy.jsonClasses;
import pl.sdacademy.entities.Country;

import java.util.List;

public interface EntityDataProvider {

    public List<Country> load();

}
