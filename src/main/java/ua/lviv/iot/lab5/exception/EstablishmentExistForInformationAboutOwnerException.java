package ua.lviv.iot.lab5.exception;

public class EstablishmentExistForInformationAboutOwnerException extends RuntimeException{
    public EstablishmentExistForInformationAboutOwnerException(Integer id) {
        super("There are available establishment for 'information_about_owner' with id=" + id);
    }
}
