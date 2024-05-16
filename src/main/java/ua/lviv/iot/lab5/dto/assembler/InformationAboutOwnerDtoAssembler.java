package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.InformationAboutOwnerController;
import ua.lviv.iot.lab5.domain.InformationAboutOwner;
import ua.lviv.iot.lab5.dto.InformationAboutOwnerDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InformationAboutOwnerDtoAssembler implements RepresentationModelAssembler<InformationAboutOwner, InformationAboutOwnerDto> {
    @Override
    public InformationAboutOwnerDto toModel(InformationAboutOwner entity) {
        InformationAboutOwnerDto informationAboutOwnerDto = InformationAboutOwnerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .age(entity.getAge())
                .fortunes(entity.getFortunes())
                .build();

        Link selfLink = linkTo(methodOn(InformationAboutOwnerController.class).getInformationAboutOwner(informationAboutOwnerDto.getId())).withSelfRel();
        informationAboutOwnerDto.add(selfLink);
        return informationAboutOwnerDto;
    }

    @Override
    public CollectionModel<InformationAboutOwnerDto> toCollectionModel(Iterable<? extends InformationAboutOwner> entities) {
        CollectionModel<InformationAboutOwnerDto> informationAboutOwnerDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(InformationAboutOwnerController.class).getAllInformationAboutOnwers()).withSelfRel();
        informationAboutOwnerDtos.add(selfLink);
        return informationAboutOwnerDtos;
    }

    public CollectionModel<InformationAboutOwnerDto> toCollectionModel(Iterable<? extends InformationAboutOwner> entities, Link link) {
        CollectionModel<InformationAboutOwnerDto> informationAboutOwnerDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        informationAboutOwnerDtos.add(link);
        return informationAboutOwnerDtos;
    }
}
