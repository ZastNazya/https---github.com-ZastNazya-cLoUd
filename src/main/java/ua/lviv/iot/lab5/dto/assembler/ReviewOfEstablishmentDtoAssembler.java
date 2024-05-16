package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.ReviewOfEstablishmentController;
import ua.lviv.iot.lab5.domain.ReviewOfEstablishment;
import ua.lviv.iot.lab5.dto.ReviewOfEstablishmentDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReviewOfEstablishmentDtoAssembler implements RepresentationModelAssembler<ReviewOfEstablishment, ReviewOfEstablishmentDto> {
    @Override
    public ReviewOfEstablishmentDto toModel(ReviewOfEstablishment entity) {
        ReviewOfEstablishmentDto reviewOfEstablishmentDto = ReviewOfEstablishmentDto.builder()
                .id(entity.getId())
                .review(entity.getReview())
                .userAccount(entity.getUserAccount().getNickname())
                .build();

        Link selfLink = linkTo(methodOn(ReviewOfEstablishmentController.class).getReviewOfEstablishment(reviewOfEstablishmentDto.getId())).withSelfRel();
        return reviewOfEstablishmentDto;
    }

    @Override
    public CollectionModel<ReviewOfEstablishmentDto> toCollectionModel(Iterable<? extends ReviewOfEstablishment> entities) {
        CollectionModel<ReviewOfEstablishmentDto> reviewOfEstablishmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ReviewOfEstablishmentController.class).getAllReviewOfEstablishments()).withSelfRel();
        reviewOfEstablishmentDtos.add(selfLink);
        return reviewOfEstablishmentDtos;
    }

    public CollectionModel<ReviewOfEstablishmentDto> toCollectionModel(Iterable<? extends ReviewOfEstablishment> entities, Link link) {
        CollectionModel<ReviewOfEstablishmentDto> reviewOfEstablishmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        reviewOfEstablishmentDtos.add(link);
        return reviewOfEstablishmentDtos;
    }
}
