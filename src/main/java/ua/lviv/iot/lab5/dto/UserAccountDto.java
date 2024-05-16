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
@Relation(itemRelation = "user_account", collectionRelation = "user_accounts")
public class UserAccountDto extends RepresentationModel<UserAccountDto> {
    private final String nickname;
    private final String name;
    private final String surname;
    private final Integer credential;
}
