package ua.lviv.iot.lab5.exception;

public class TypeOfEstablishmentNotFoundException extends RuntimeException {
    public TypeOfEstablishmentNotFoundException(Integer id) {
        super("Could not find 'type_of_establishment' with id=" + id);
    }
}
