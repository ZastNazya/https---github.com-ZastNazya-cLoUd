package ua.lviv.iot.lab5.exception;

public class CredentialNotFoundException extends RuntimeException {
    public CredentialNotFoundException(Integer id) {
        super("Could not find 'credential' with id=" + id);
    }
}
