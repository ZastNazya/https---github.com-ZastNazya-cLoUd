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
import ua.lviv.iot.lab5.domain.Establishment;
import ua.lviv.iot.lab5.domain.ReviewOfEstablishment;
import ua.lviv.iot.lab5.dto.EstablishmentDto;
import ua.lviv.iot.lab5.dto.ReviewOfEstablishmentDto;
import ua.lviv.iot.lab5.dto.assembler.EstablishmentDtoAssembler;
import ua.lviv.iot.lab5.dto.assembler.ReviewOfEstablishmentDtoAssembler;
import ua.lviv.iot.lab5.service.EstablishmentService;

import java.util.List;

@RestController
@RequestMapping("/api/establishment")
public class EstablishmentController {
    private final EstablishmentService establishmentService;
    private final EstablishmentDtoAssembler establishmentDtoAssembler;
    private final ReviewOfEstablishmentDtoAssembler reviewOfEstablishmentDtoAssembler;

    @Autowired
    public EstablishmentController(EstablishmentService establishmentService, EstablishmentDtoAssembler establishmentDtoAssembler, ReviewOfEstablishmentDtoAssembler reviewOfEstablishmentDtoAssembler) {
        this.establishmentService = establishmentService;
        this.establishmentDtoAssembler = establishmentDtoAssembler;
        this.reviewOfEstablishmentDtoAssembler = reviewOfEstablishmentDtoAssembler;
    }

    @GetMapping(value = "/{establishmentId}")
    public ResponseEntity<EstablishmentDto> getEstablishment(@PathVariable("establishmentId") Integer establishmentId) {
        Establishment establishment = establishmentService.findById(establishmentId);
        EstablishmentDto establishmentDto = establishmentDtoAssembler.toModel(establishment);
        return new ResponseEntity<>(establishmentDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EstablishmentDto>> getAllEstablishments() {
        List<Establishment> establishments = establishmentService.findAll();
        CollectionModel<EstablishmentDto> establishmentDtos = establishmentDtoAssembler.toCollectionModel(establishments);
        return new ResponseEntity<>(establishmentDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EstablishmentDto> addEstablishment(@RequestBody Establishment establishment) {
        Establishment newEstablishment = establishmentService.create(establishment);
        EstablishmentDto establishmentDto = establishmentDtoAssembler.toModel(newEstablishment);
        return new ResponseEntity<>(establishmentDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{establishmentId}")
    public ResponseEntity<?> updateEstablishment(@RequestBody Establishment establishment, @PathVariable("establishmentId") Integer establishmentId) {
        establishmentService.update(establishmentId, establishment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{credentialId}")
    public ResponseEntity<?> deleteEstablishment(@PathVariable("credentialId") Integer establishmentId) {
        establishmentService.delete(establishmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{establishmentId}/review")
    public ResponseEntity<CollectionModel<ReviewOfEstablishmentDto>> findReviewByEstablishmentId(@PathVariable("establishmentId") Integer establishmentId) {
        List<ReviewOfEstablishment> reviewOfEstablishments = establishmentService.findReviewByEstablishmentId(establishmentId);
        CollectionModel<ReviewOfEstablishmentDto> reviewOfEstablishmentDtos = reviewOfEstablishmentDtoAssembler.toCollectionModel(reviewOfEstablishments);
        return new ResponseEntity<>(reviewOfEstablishmentDtos, HttpStatus.OK);
    }

    @PostMapping(value = "/dymanic_procedure")
    public ResponseEntity<?> dymanicProcedure() {
        establishmentService.dymanicProcedure();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
