package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.Credential;
import ua.lviv.iot.lab5.exception.CredentialNotFoundException;
import ua.lviv.iot.lab5.exception.UserAccountExistForCredentialException;
import ua.lviv.iot.lab5.repository.CredentialRepository;
import ua.lviv.iot.lab5.service.CredentialService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {
    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialServiceImpl(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public List<Credential> findAll() {
        return credentialRepository.findAll();
    }

    @Override
    public Credential findById(Integer id) {
        return credentialRepository.findById(id)
                .orElseThrow(() -> new CredentialNotFoundException(id));
    }

    @Override
    @Transactional
    public Credential create(Credential credential) {
        credentialRepository.save(credential);
        return credential;
    }

    @Override
    @Transactional
    public void update(Integer id, Credential uCredential) {
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> new CredentialNotFoundException(id));
        credential.setLogin(uCredential.getLogin());
        credential.setPassword(uCredential.getPassword());
        credentialRepository.save(credential);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> new CredentialNotFoundException(id));
        if(!credential.getUserAccounts().isEmpty()) throw new UserAccountExistForCredentialException(id);
        credentialRepository.delete(credential);
    }

    @Override
    @Transactional
    public void insert10Credential() {
        credentialRepository.insert10Credential();
    }
}
