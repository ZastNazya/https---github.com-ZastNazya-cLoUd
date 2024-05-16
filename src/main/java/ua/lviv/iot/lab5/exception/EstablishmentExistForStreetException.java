package ua.lviv.iot.lab5.exception;

public class EstablishmentExistForStreetException extends RuntimeException {
    public EstablishmentExistForStreetException(Integer id) {
        super("There are available establishment for 'street' with id=" + id);
    }
}
