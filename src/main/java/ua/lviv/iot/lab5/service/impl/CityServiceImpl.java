package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.City;
import ua.lviv.iot.lab5.exception.CityNotFoundException;
import ua.lviv.iot.lab5.exception.StreetExistForCityException;
import ua.lviv.iot.lab5.repository.CityRepository;
import ua.lviv.iot.lab5.service.CityService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Integer id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

    @Override
    public City create(City city) {
        cityRepository.save(city);
        return city;
    }

    @Override
    @Transactional
    public void update(Integer id, City uCity) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        city.setName(uCity.getName());
        city.setCountry(uCity.getCountry());
        cityRepository.save(city);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
        if (!city.getStreets().isEmpty()) throw new StreetExistForCityException(id);
        cityRepository.delete(city);
    }
}
