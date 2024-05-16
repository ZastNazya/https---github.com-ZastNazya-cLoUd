package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.EstablishmentController;
import ua.lviv.iot.lab5.domain.Establishment;
import ua.lviv.iot.lab5.dto.EstablishmentDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EstablishmentDtoAssembler implements RepresentationModelAssembler<Establishment, EstablishmentDto> {
    @Override
    public EstablishmentDto toModel(Establishment entity) {
        EstablishmentDto establishmentDto = EstablishmentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .rating(entity.getRating())
                .typeOfEstablishment(entity.getTypeOfEstablishment().getTypeName())
                .informationAboutOwner(entity.getInformationAboutOwner().getId())
                .street(entity.getStreet().getName())
                .build();

        Link selfLink = linkTo(methodOn(EstablishmentController.class).getEstablishment(establishmentDto.getId())).withSelfRel();
        establishmentDto.add(selfLink);
        return establishmentDto;
    }

    @Override
    public CollectionModel<EstablishmentDto> toCollectionModel(Iterable<? extends Establishment> entities) {
        CollectionModel<EstablishmentDto> establishmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EstablishmentController.class).getAllEstablishments()).withSelfRel();
        establishmentDtos.add(selfLink);
        return establishmentDtos;
    }

    public CollectionModel<EstablishmentDto> toCollectionModel(Iterable<? extends Establishment> entities, Link link) {
        CollectionModel<EstablishmentDto> establishmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        establishmentDtos.add(link);
        return establishmentDtos;
    }
}
