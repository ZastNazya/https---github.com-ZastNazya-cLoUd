package ua.lviv.iot.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.lab5.domain.City;
import ua.lviv.iot.lab5.domain.Country;
import ua.lviv.iot.lab5.dto.CityDto;
import ua.lviv.iot.lab5.dto.CountryDto;
import ua.lviv.iot.lab5.dto.assembler.CityDtoAssembler;
import ua.lviv.iot.lab5.dto.assembler.CountryDtoAssembler;
import ua.lviv.iot.lab5.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    private final CountryService countryService;
    private final CountryDtoAssembler countryDtoAssembler;
    private final CityDtoAssembler cityDtoAssembler;

    @Autowired
    public CountryController(CountryService countryService, CountryDtoAssembler countryDtoAssembler, CityDtoAssembler cityDtoAssembler) {
        this.countryService = countryService;
        this.countryDtoAssembler = countryDtoAssembler;
        this.cityDtoAssembler = cityDtoAssembler;
    }

    @GetMapping(value = "/{countryId}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable("countryId") String countryId) {
        Country country = countryService.findById(countryId);
        CountryDto countryDto = countryDtoAssembler.toModel(country);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CountryDto>> getAllCountries() {
        List<Country> countries = countryService.findAll();
        CollectionModel<CountryDto> countryDtos = countryDtoAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(countryDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CountryDto> addCountry(@RequestBody Country country) {
        Country newCountry = countryService.create(country);
        CountryDto countryDto = countryDtoAssembler.toModel(newCountry);
        return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody Country country, @PathVariable("countryId") String countryId) {
        countryService.update(countryId, country);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable("countryId") String countryId) {
        countryService.delete(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{countryId}/cities")
    public ResponseEntity<CollectionModel<CityDto>> findCityByCountryId(@PathVariable("countryId") String countryId) {
        List<City> cities = countryService.findCityByCountryId(countryId);
        CollectionModel<CityDto> cityDtos = cityDtoAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }
}
