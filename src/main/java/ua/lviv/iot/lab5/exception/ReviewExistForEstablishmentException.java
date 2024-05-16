package ua.lviv.iot.lab5.exception;

public class ReviewExistForEstablishmentException extends RuntimeException {
    public ReviewExistForEstablishmentException(Integer id) {
        super("There are available reviews for 'establishment' with id=" + id);
    }
}
