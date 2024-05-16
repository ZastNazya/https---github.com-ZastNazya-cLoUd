package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.StreetController;
import ua.lviv.iot.lab5.domain.Street;
import ua.lviv.iot.lab5.dto.StreetDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StreetDtoAssembler implements RepresentationModelAssembler<Street, StreetDto> {
    @Override
    public StreetDto toModel(Street entity) {
        StreetDto streetDto = StreetDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .city(entity.getCity().getName())
                .build();
        Link selfLink = linkTo(methodOn(StreetController.class).getStreet(streetDto.getId())).withSelfRel();
        streetDto.add(selfLink);
        return streetDto;
    }

    @Override
    public CollectionModel<StreetDto> toCollectionModel(Iterable<? extends Street> entities) {
        CollectionModel<StreetDto> streetDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(StreetController.class).getAllStreets()).withSelfRel();
        streetDtos.add(selfLink);
        return streetDtos;
    }

    public CollectionModel<StreetDto> toCollectionModel(Iterable<? extends Street> entities, Link link) {
        CollectionModel<StreetDto> streetDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        streetDtos.add(link);
        return streetDtos;
    }
}
