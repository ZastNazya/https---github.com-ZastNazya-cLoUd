package ua.lviv.iot.lab5.exception;

public class ReviewOfEstablishmentNotFoundException extends RuntimeException {
    public ReviewOfEstablishmentNotFoundException(Integer id) {
        super("Could not find 'review_of_establishment' with id=" + id);
    }
}
