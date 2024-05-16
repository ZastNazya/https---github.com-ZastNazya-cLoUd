package ua.lviv.iot.lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "type_of_establishment", collectionRelation = "type_of_establishments")
public class TypeOfEstablishmentDto extends RepresentationModel<TypeOfEstablishmentDto> {
    private final Integer id;
    private final String typeName;
}
