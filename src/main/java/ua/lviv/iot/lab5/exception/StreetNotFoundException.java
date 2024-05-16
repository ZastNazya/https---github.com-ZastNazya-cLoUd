package ua.lviv.iot.lab5.exception;

public class StreetNotFoundException extends RuntimeException {
    public StreetNotFoundException(Integer id) {
        super("Could not find 'street' with id=" + id);
    }
}
