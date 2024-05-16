package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.CredentialController;
import ua.lviv.iot.lab5.domain.Credential;
import ua.lviv.iot.lab5.dto.CredentialDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CredentialDtoAssembler implements RepresentationModelAssembler<Credential, CredentialDto> {
    @Override
    public CredentialDto toModel(Credential entity) {
        CredentialDto credentialDto = CredentialDto.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .password(entity.getPassword())
                .build();

        Link selfLink = linkTo(methodOn(CredentialController.class).getCredential(credentialDto.getId())).withSelfRel();
        credentialDto.add(selfLink);
        return credentialDto;
    }

    @Override
    public CollectionModel<CredentialDto> toCollectionModel(Iterable<? extends Credential> entities) {
        CollectionModel<CredentialDto> credentialDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CredentialController.class).getAllCredentials()).withSelfRel();
        credentialDtos.add(selfLink);
        return credentialDtos;
    }

    public CollectionModel<CredentialDto> toCollectionModel(Iterable<? extends Credential> entities, Link link) {
        CollectionModel<CredentialDto> credentialDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        credentialDtos.add(link);
        return credentialDtos;
    }
}
