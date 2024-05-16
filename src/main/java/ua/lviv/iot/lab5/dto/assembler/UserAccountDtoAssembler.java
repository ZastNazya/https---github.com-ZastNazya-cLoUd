package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.UserAccountController;
import ua.lviv.iot.lab5.domain.UserAccount;
import ua.lviv.iot.lab5.dto.UserAccountDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAccountDtoAssembler implements RepresentationModelAssembler<UserAccount, UserAccountDto> {
    @Override
    public UserAccountDto toModel(UserAccount entity) {
        UserAccountDto userAccountDto = UserAccountDto.builder()
                .nickname(entity.getNickname())
                .name(entity.getName())
                .surname(entity.getSurname())
                .credential(entity.getCredential().getId())
                .build();

        Link selfLink = linkTo(methodOn(UserAccountController.class).getUser(userAccountDto.getNickname())).withSelfRel();
        userAccountDto.add(selfLink);
        return userAccountDto;
    }

    @Override
    public CollectionModel<UserAccountDto> toCollectionModel(Iterable<? extends UserAccount> entities) {
        CollectionModel<UserAccountDto> userAccountDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(UserAccountController.class).getAllUsers()).withSelfRel();
        userAccountDtos.add(selfLink);
        return userAccountDtos;
    }

    public CollectionModel<UserAccountDto> toCollectionModel(Iterable<? extends UserAccount> entities, Link link) {
        CollectionModel<UserAccountDto> userAccountDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        userAccountDtos.add(link);
        return userAccountDtos;
    }
}
