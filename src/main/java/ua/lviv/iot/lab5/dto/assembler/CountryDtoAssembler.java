package ua.lviv.iot.lab5.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import ua.lviv.iot.lab5.controller.CountryController;
import ua.lviv.iot.lab5.domain.Country;
import ua.lviv.iot.lab5.dto.CountryDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CountryDtoAssembler implements RepresentationModelAssembler<Country, CountryDto> {
    @Override
    public CountryDto toModel(Country entity) {
        CountryDto countryDto = CountryDto.builder()
                .name(entity.getName())
                .build();

        Link selfLink = linkTo(methodOn(CountryController.class).getCountry(countryDto.getName())).withSelfRel();
        countryDto.add(selfLink);
        return countryDto;
    }

    @Override
    public CollectionModel toCollectionModel(Iterable<? extends Country> entities) {
        CollectionModel<CountryDto> countryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();
        countryDtos.add(selfLink);
        return countryDtos;
    }

    public CollectionModel toCollectionModel(Iterable<? extends Country> entities, Link link) {
        CollectionModel<CountryDto> countryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        countryDtos.add(link);
        return countryDtos;
    }
}
