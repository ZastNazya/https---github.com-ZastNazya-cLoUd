package ua.lviv.iot.lab5.exception;

public class EstablishmentNotFoundException extends RuntimeException {
    public EstablishmentNotFoundException(Integer id) {
        super("Could not find 'establishment' with id=" + id);
    }
}
