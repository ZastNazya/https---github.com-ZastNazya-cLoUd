package ua.lviv.iot.lab5.exception;

public class ReviewExistForReviewOfEstablishmentException extends RuntimeException {
    public ReviewExistForReviewOfEstablishmentException(Integer id) {
        super("There are available reviews for 'review_of_establishment' with id=" + id);
    }
}
