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
import ua.lviv.iot.lab5.dto.CityDto;
import ua.lviv.iot.lab5.dto.assembler.CityDtoAssembler;
import ua.lviv.iot.lab5.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {
    private final CityService cityService;
    private final CityDtoAssembler cityDtoAssembler;

    @Autowired
    public CityController(CityService cityService, CityDtoAssembler cityDtoAssembler) {
        this.cityService = cityService;
        this.cityDtoAssembler = cityDtoAssembler;
    }

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<CityDto> getCity(@PathVariable("cityId") Integer cityId) {
        City city = cityService.findById(cityId);
        CityDto cityDto = cityDtoAssembler.toModel(city);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CityDto>> getAllCities() {
        List<City> cities = cityService.findAll();
        CollectionModel<CityDto> cityDtos = cityDtoAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CityDto> addCity(@RequestBody City city) {
        City newCity = cityService.create(city);
        CityDto cityDto = cityDtoAssembler.toModel(newCity);
        return new ResponseEntity<>(cityDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> updateCity(@RequestBody City uCity, @PathVariable("cityId") Integer cityId) {
        cityService.update(cityId, uCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable("cityId") Integer cityId) {
        cityService.delete(cityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
