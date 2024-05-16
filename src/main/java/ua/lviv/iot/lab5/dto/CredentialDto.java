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
@Relation(itemRelation = "credential", collectionRelation = "credentials")
public class CredentialDto extends RepresentationModel<CredentialDto> {
    private final Integer id;
    private final String login;
    private final String password;
}
