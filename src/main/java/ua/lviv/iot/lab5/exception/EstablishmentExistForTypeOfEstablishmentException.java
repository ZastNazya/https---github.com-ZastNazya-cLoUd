package ua.lviv.iot.lab5.exception;

public class EstablishmentExistForTypeOfEstablishmentException extends RuntimeException {
    public EstablishmentExistForTypeOfEstablishmentException(Integer id) {
        super("There are available establishment for 'type_of_establishment' with id=" + id);
    }
}
