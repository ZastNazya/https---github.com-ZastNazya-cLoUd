package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.City;
import ua.lviv.iot.lab5.domain.Country;
import ua.lviv.iot.lab5.exception.CityExistForCountryException;
import ua.lviv.iot.lab5.exception.CountryNotFoundException;
import ua.lviv.iot.lab5.repository.CountryRepository;
import ua.lviv.iot.lab5.service.CountryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(String name) {
        return countryRepository.findById(name).orElseThrow(() -> new CountryNotFoundException(name));
    }

    @Override
    @Transactional
    public Country create(Country country) {
        countryRepository.save(country);
        return country;
    }

    @Override
    @Transactional
    public void update(String name, Country uCountry) {
        Country country = countryRepository.findById(name)
                .orElseThrow(()-> new CountryNotFoundException(name));
        country.setName(uCountry.getName());
        countryRepository.save(country);
    }

    @Override
    @Transactional
    public void delete(String name) {
        Country country = countryRepository.findById(name)
                .orElseThrow(() -> new CountryNotFoundException(name));
        if(!country.getCities().isEmpty()) throw new CityExistForCountryException(name);
        countryRepository.delete(country);
    }

    @Override
    @Transactional
    public List<City> findCityByCountryId(String nameId) {
        Country country = countryRepository.findById(nameId)
                .orElseThrow(() -> new CountryNotFoundException(nameId));
        return country.getCities();
    }
}
