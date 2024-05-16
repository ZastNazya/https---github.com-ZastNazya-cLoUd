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
import ua.lviv.iot.lab5.domain.Street;
import ua.lviv.iot.lab5.dto.StreetDto;
import ua.lviv.iot.lab5.dto.assembler.StreetDtoAssembler;
import ua.lviv.iot.lab5.service.StreetService;

import java.util.List;

@RestController
@RequestMapping("/api/street")
public class StreetController {
    private final StreetService streetService;
    private final StreetDtoAssembler streetDtoAssembler;

    @Autowired
    public StreetController(StreetService streetService, StreetDtoAssembler streetDtoAssembler) {
        this.streetService = streetService;
        this.streetDtoAssembler = streetDtoAssembler;
    }

    @GetMapping(value = "/{streetId}")
    public ResponseEntity<StreetDto> getStreet(@PathVariable("streetId") Integer streetId) {
        Street street = streetService.findById(streetId);
        StreetDto streetDto = streetDtoAssembler.toModel(street);
        return new ResponseEntity<>(streetDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<StreetDto>> getAllStreets() {
        List<Street> streets = streetService.findAll();
        CollectionModel<StreetDto> streetDtos = streetDtoAssembler.toCollectionModel(streets);
        return new ResponseEntity<>(streetDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<StreetDto> addStreet(@RequestBody Street street) {
        Street newStreet = streetService.create(street);
        StreetDto streetDto = streetDtoAssembler.toModel(newStreet);
        return new ResponseEntity<>(streetDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{streetId}")
    public ResponseEntity<?> updateStreet(@RequestBody Street street, @PathVariable("streetId") Integer streetId) {
        streetService.update(streetId, street);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteStreet(@PathVariable("countryId") Integer streetId) {
        streetService.delete(streetId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
