package ua.lviv.iot.lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "establishment", collectionRelation = "establishments")
public class EstablishmentDto extends RepresentationModel<EstablishmentDto> {
    private final Integer id;
    private final String name;
    private final Double rating;
    private final String typeOfEstablishment;
    private final String street;
    private final Integer informationAboutOwner;
}