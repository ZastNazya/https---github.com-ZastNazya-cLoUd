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
import ua.lviv.iot.lab5.domain.Credential;
import ua.lviv.iot.lab5.dto.CredentialDto;
import ua.lviv.iot.lab5.dto.assembler.CredentialDtoAssembler;
import ua.lviv.iot.lab5.service.CredentialService;

import java.util.List;

@RestController
@RequestMapping("/api/credential")
public class CredentialController {
    private final CredentialService credentialService;
    private final CredentialDtoAssembler credentialDtoAssembler;

    @Autowired
    public CredentialController(CredentialService credentialService, CredentialDtoAssembler credentialDtoAssembler) {
        this.credentialService = credentialService;
        this.credentialDtoAssembler = credentialDtoAssembler;
    }

    @GetMapping(value = "/{credentialId}")
    public ResponseEntity<CredentialDto> getCredential(@PathVariable("credentialId") Integer credentialId) {
        Credential credential = credentialService.findById(credentialId);
        CredentialDto credentialDto = credentialDtoAssembler.toModel(credential);
        return new ResponseEntity<>(credentialDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CredentialDto>> getAllCredentials() {
        List<Credential> credentials = credentialService.findAll();
        CollectionModel<CredentialDto> credentialDtos = credentialDtoAssembler.toCollectionModel(credentials);
        return new ResponseEntity<>(credentialDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CredentialDto> addCredential(@RequestBody Credential credential) {
        Credential newCredential = credentialService.create(credential);
        CredentialDto credentialDto = credentialDtoAssembler.toModel(newCredential);
        return new ResponseEntity<>(credentialDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{credentialId}")
    public ResponseEntity<?> updateCredential(@RequestBody Credential credential, @PathVariable("credentialId") Integer credentialId) {
        credentialService.update(credentialId, credential);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{credentialId}")
    public ResponseEntity<?> deleteCredential(@PathVariable("credentialId") Integer credentialId) {
        credentialService.delete(credentialId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> insert10Credential() {
        credentialService.insert10Credential();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
