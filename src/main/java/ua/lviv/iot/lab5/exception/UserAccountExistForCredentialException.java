package ua.lviv.iot.lab5.exception;

public class UserAccountExistForCredentialException extends RuntimeException{
    public UserAccountExistForCredentialException(Integer id) {
        super("There are available user_accounts for 'credential' with id=" + id);
    }
}
