package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.TypeOfEstablishmentController;
import ua.lviv.iot.lab5.domain.TypeOfEstablishment;
import ua.lviv.iot.lab5.dto.TypeOfEstablishmentDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TypeOfEstablishmentDtoAssembler implements RepresentationModelAssembler<TypeOfEstablishment, TypeOfEstablishmentDto> {
    @Override
    public TypeOfEstablishmentDto toModel(TypeOfEstablishment entity) {
        TypeOfEstablishmentDto typeOfEstablishmentDto = TypeOfEstablishmentDto.builder()
                .id(entity.getId())
                .typeName(entity.getTypeName())
                .build();

        Link selfLink = linkTo(methodOn(TypeOfEstablishmentController.class).getTypeOfEstablishment(typeOfEstablishmentDto.getId())).withSelfRel();
        typeOfEstablishmentDto.add(selfLink);
        return typeOfEstablishmentDto;
    }

    @Override
    public CollectionModel<TypeOfEstablishmentDto> toCollectionModel(Iterable<? extends TypeOfEstablishment> entities) {
        CollectionModel<TypeOfEstablishmentDto> typeOfEstablishmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TypeOfEstablishmentController.class).getAllTypeOfEstablishments()).withSelfRel();
        typeOfEstablishmentDtos.add(selfLink);
        return typeOfEstablishmentDtos;
    }

    public CollectionModel<TypeOfEstablishmentDto> toCollectionModel(Iterable<? extends TypeOfEstablishment> entities, Link link) {
        CollectionModel<TypeOfEstablishmentDto> typeOfEstablishmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        typeOfEstablishmentDtos.add(link);
        return typeOfEstablishmentDtos;
    }
}
