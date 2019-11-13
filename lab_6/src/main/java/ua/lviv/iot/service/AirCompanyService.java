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
public class AirCompanyService {
    @Autowired
    AirCompanyRepository airCompanyRepository;

    @Autowired
    CountryRepository countryRepository;

    public List<AirCompanyEntity> getAirCompaniesByCountryId(Integer id) throws NoSuchElementException {
        CountryEntity countryEntity = countryRepository.findById(id).get();
        if (countryEntity == null) throw new NoSuchElementException();
        return countryEntity.getAirCompanyEntities();
    }

    public AirCompanyEntity getAirCompany(Integer id) throws NoSuchElementException {
        AirCompanyEntity airCompanyEntity = airCompanyRepository.findById(id).get();
        if (airCompanyEntity == null) throw new NoSuchElementException();
        return airCompanyEntity;
    }

    public List<AirCompanyEntity> getAllAirCompanies() {
        return airCompanyRepository.findAll();
    }

    @Transactional
    public void createAirCompany(AirCompanyEntity airCompanyEntity, Integer country_id) {
        CountryEntity countryEntity = countryRepository.findById(country_id).get();
        if (countryEntity == null) throw new NoSuchElementException();
        airCompanyEntity.setCountryEntity(countryEntity);
        airCompanyRepository.save(airCompanyEntity);
    }

    @Transactional
    public void updateAirCompany(AirCompanyEntity uAirCompanyEntity, Integer id, Integer country_id) throws NoSuchElementException {
        AirCompanyEntity airCompanyEntity = airCompanyRepository.findById(id).get();
        if (airCompanyEntity == null) throw new NoSuchElementException();
        CountryEntity countryEntity = countryRepository.findById(country_id).get();
        if (countryEntity == null) throw new NoSuchElementException();
        airCompanyEntity.setCountryEntity(countryEntity);
        airCompanyEntity.setName(uAirCompanyEntity.getName());
    }

    @Transactional
    public void deleteAirCompany(Integer id) throws NoSuchElementException {
        AirCompanyEntity airCompanyEntity = airCompanyRepository.findById(id).get();

        if (airCompanyEntity == null) throw new NoSuchElementException();
        airCompanyRepository.delete(airCompanyEntity);
    }
}
