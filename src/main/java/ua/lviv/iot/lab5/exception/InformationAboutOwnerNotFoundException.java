package ua.lviv.iot.lab5.exception;

public class InformationAboutOwnerNotFoundException extends RuntimeException {
    public InformationAboutOwnerNotFoundException(Integer id) {
        super("Could not find 'information_about_owner' with id=" + id);
    }
}
