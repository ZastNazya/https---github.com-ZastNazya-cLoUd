package ua.lviv.iot.lab5.exception;

public class ReviewOfEstablishmentExistForUserAccountException extends RuntimeException {
    public ReviewOfEstablishmentExistForUserAccountException(String nickname) {
        super("There are available review_of_establishments for 'user_account' with id=" + nickname);
    }
}
