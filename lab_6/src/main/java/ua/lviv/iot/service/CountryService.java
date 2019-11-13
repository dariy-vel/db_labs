package ua.lviv.iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.model.AirCompanyEntity;
import ua.lviv.iot.model.CountryEntity;
import ua.lviv.iot.repository.AirCompanyRepository;
import ua.lviv.iot.repository.CountryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    AirCompanyRepository airCompanyRepository;

    public CountryEntity getCountryByAirCompanyId(Integer id) throws NoSuchElementException{
        AirCompanyEntity airCompanyEntity = airCompanyRepository.findById(id).get();//2.0.0.M7
        if (airCompanyEntity == null) throw new NoSuchElementException();
        return airCompanyEntity.getCountryEntity();
    }

    public CountryEntity getCountry(Integer id) throws NoSuchElementException {
        CountryEntity country = countryRepository.findById(id).get();
        if (country == null) throw new NoSuchElementException();
        return country;
    }

    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional
    public void createCountry(CountryEntity country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(CountryEntity uCountry, Integer id) throws NoSuchElementException {
        CountryEntity country = countryRepository.findById(id).get();
        if (country == null) throw new NoSuchElementException();

        country.setName(uCountry.getName());
    }

    @Transactional
    public void deleteCountry(Integer id) throws NoSuchElementException {
        CountryEntity country = countryRepository.findById(id).get();

        if (country == null) throw new NoSuchElementException();
        countryRepository.delete(country);
    }
}
