package ua.lviv.iot.lab5.exception;

public class StreetExistForCityException extends RuntimeException {
    public StreetExistForCityException(Integer id) {
        super("There are available streets for 'city' with id=" + id);
    }
}
