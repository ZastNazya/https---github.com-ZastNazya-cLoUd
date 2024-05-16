package ua.lviv.iot.lab5.exception;

public class UserAccountNotFoundException extends RuntimeException {
    public UserAccountNotFoundException(String nickname) {
        super("Could not find 'user_account' with nickname=" + nickname);
    }
}
