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
@Relation(itemRelation = "review_of_establishment", collectionRelation = "review_of_establishments")
public class ReviewOfEstablishmentDto extends RepresentationModel<ReviewOfEstablishmentDto> {
    private final Integer id;
    private final String review;
    private final String userAccount;
}
