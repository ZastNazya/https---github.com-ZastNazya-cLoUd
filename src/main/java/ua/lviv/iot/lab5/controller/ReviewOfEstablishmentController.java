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
import ua.lviv.iot.lab5.service.ReviewOfEstablishmentService;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewOfEstablishmentController {
    private final ReviewOfEstablishmentService reviewOfEstablishmentService;
    private final ReviewOfEstablishmentDtoAssembler reviewOfEstablishmentDtoAssembler;
    private final EstablishmentDtoAssembler establishmentDtoAssembler;

    @Autowired
    public ReviewOfEstablishmentController(ReviewOfEstablishmentService reviewOfEstablishmentService, ReviewOfEstablishmentDtoAssembler reviewOfEstablishmentDtoAssembler, EstablishmentDtoAssembler establishmentDtoAssembler) {
        this.reviewOfEstablishmentService = reviewOfEstablishmentService;
        this.reviewOfEstablishmentDtoAssembler = reviewOfEstablishmentDtoAssembler;
        this.establishmentDtoAssembler = establishmentDtoAssembler;
    }

    @GetMapping(value = "/{reviewOfEstablishmentId}")
    public ResponseEntity<ReviewOfEstablishmentDto> getReviewOfEstablishment(@PathVariable("reviewOfEstablishmentId") Integer reviewOfEstablishmentId) {
        ReviewOfEstablishment reviewOfEstablishment = reviewOfEstablishmentService.findById(reviewOfEstablishmentId);
        ReviewOfEstablishmentDto reviewOfEstablishmentDto = reviewOfEstablishmentDtoAssembler.toModel(reviewOfEstablishment);
        return new ResponseEntity<>(reviewOfEstablishmentDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ReviewOfEstablishmentDto>> getAllReviewOfEstablishments() {
        List<ReviewOfEstablishment> reviewOfEstablishments = reviewOfEstablishmentService.findAll();
        CollectionModel<ReviewOfEstablishmentDto> reviewOfEstablishmentDtos = reviewOfEstablishmentDtoAssembler.toCollectionModel(reviewOfEstablishments);
        return new ResponseEntity<>(reviewOfEstablishmentDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ReviewOfEstablishmentDto> addReviewOfEstablishment(@RequestBody ReviewOfEstablishment reviewOfEstablishment) {
        ReviewOfEstablishment newReviewOfEstablishment = reviewOfEstablishmentService.create(reviewOfEstablishment);
        ReviewOfEstablishmentDto reviewOfEstablishmentDto = reviewOfEstablishmentDtoAssembler.toModel(newReviewOfEstablishment);
        return new ResponseEntity<>(reviewOfEstablishmentDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{establishmentId}")
    public ResponseEntity<?> updateReviewOfEstablishment(@RequestBody ReviewOfEstablishment reviewOfEstablishment, @PathVariable("establishmentId") Integer reviewOfEstablishmentId) {
        reviewOfEstablishmentService.update(reviewOfEstablishmentId, reviewOfEstablishment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{credentialId}")
    public ResponseEntity<?> deleteReviewOfEstablishment(@PathVariable("credentialId") Integer reviewOfEstablishmentId) {
        reviewOfEstablishmentService.delete(reviewOfEstablishmentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{reviewId}/establishment")
    public ResponseEntity<CollectionModel<EstablishmentDto>> findEstablishmentByReviewId(@PathVariable("reviewId") Integer reviewId) {
        List<Establishment> establishments = reviewOfEstablishmentService.findEstablishmentByReviewId(reviewId);
        CollectionModel<EstablishmentDto> reviewOfEstablishmentDtos = establishmentDtoAssembler.toCollectionModel(establishments);
        return new ResponseEntity<>(reviewOfEstablishmentDtos, HttpStatus.OK);
    }
}
