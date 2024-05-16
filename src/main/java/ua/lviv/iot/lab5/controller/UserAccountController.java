package ua.lviv.iot.lab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.lab5.domain.UserAccount;
import ua.lviv.iot.lab5.dto.UserAccountDto;
import ua.lviv.iot.lab5.dto.assembler.UserAccountDtoAssembler;
import ua.lviv.iot.lab5.service.UserAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/user_account")
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final UserAccountDtoAssembler userAccountDtoAssembler;

    @Autowired
    public UserAccountController(UserAccountService userAccountService, UserAccountDtoAssembler userAccountDtoAssembler) {
        this.userAccountService = userAccountService;
        this.userAccountDtoAssembler = userAccountDtoAssembler;
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserAccountDto> getUser(@PathVariable("userId") String userId) {
        UserAccount userAccount = userAccountService.findById(userId);
        UserAccountDto userAccountDto = userAccountDtoAssembler.toModel(userAccount);
        return new ResponseEntity<>(userAccountDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<UserAccountDto>> getAllUsers() {
        List<UserAccount> userAccounts = userAccountService.findAll();
        CollectionModel<UserAccountDto> userAccountDtos = userAccountDtoAssembler.toCollectionModel(userAccounts);
        return new ResponseEntity<>(userAccountDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserAccountDto> addUserAccount(@RequestBody UserAccount userAccount) {
        UserAccount newUserAccount = userAccountService.create(userAccount);
        UserAccountDto userAccountDto = userAccountDtoAssembler.toModel(newUserAccount);
        return new ResponseEntity<>(userAccountDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateUserAccount(@RequestBody UserAccount userAccount, @PathVariable("userId") String userId) {
        userAccountService.update(userId, userAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<?> deleteUserAccount(@PathVariable("userId") String userId) {
        userAccountService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
