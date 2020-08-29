package pl.sdacademy;

import org.junit.Test;
import pl.sdacademy.entities.Country;
import pl.sdacademy.jsonClasses.ApiDataProvider;
import pl.sdacademy.jsonClasses.ApiObjectToEntityMapper;
import pl.sdacademy.jsonClasses.Summary;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiObjectToEntityMapper_Test {
    private Summary summary;

    @Test
    public void ApiObjectToEntityMapper_TestingMapperSizeCountries() {
        //given
        if (summary == null) summary = ApiDataProvider.apiDataProvider();
        //when
        assert summary != null;
        List<Country> countries = ApiObjectToEntityMapper.map(summary);
        //then
        assertEquals("Test mapper size countries", countries.size(), summary.getCountries().size()+1);
    }

    @Test
    public void ApiObjectToEntityMapper_TestingMapperNameAllCountries() {
        //given
        if (summary == null) summary = ApiDataProvider.apiDataProvider();
        //when
        assert summary != null;
        List<Country> countries = ApiObjectToEntityMapper.map(summary);
        //then
        assertTrue("Test mapper name all countries", verificationName(countries));
    }

    private boolean verificationName(List<Country> countries){
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        countries.forEach(country -> {
            if(verificationNameSummary(country.getName())) result.set(true);
        });
        return result.get();
    }

    private Boolean verificationNameSummary(String name){
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        summary.getCountries().forEach(country ->{
           if(name.equals(country.getCountry()))
               result.set(true);
        });
        return result.get();
    }

    @Test
    public void ApiObjectToEntityMapper_TestingMapperAllCountryCodeName() {
        //given
        if (summary == null) summary = ApiDataProvider.apiDataProvider();
        //when
        assert summary != null;
        List<Country> countries = ApiObjectToEntityMapper.map(summary);
        //then
        assertTrue("Test mapper country code", verificationCodeNameSummary(countries));
    }
    private boolean verificationCodeNameSummary(List<Country> countries){
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        countries.forEach(country -> {
            if(verificationCodeNameSummary(country.getCodeName())) result.set(true);
        });
        return result.get();
    }

    private Boolean verificationCodeNameSummary(String code){
        AtomicReference<Boolean> result = new AtomicReference<>(false);
        summary.getCountries().forEach(country ->{
            if(code.equals(country.getCountryCode()))
                result.set(true);
        });
        return result.get();
    }
}
