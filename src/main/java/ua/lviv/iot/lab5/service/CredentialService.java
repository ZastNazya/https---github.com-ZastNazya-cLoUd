package ua.lviv.iot.lab5.service;

import ua.lviv.iot.lab5.domain.Credential;

public interface CredentialService extends GeneralService<Credential, Integer> {
    void insert10Credential();
}
