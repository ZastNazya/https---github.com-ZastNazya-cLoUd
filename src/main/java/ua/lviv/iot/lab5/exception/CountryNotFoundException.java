package ua.lviv.iot.lab5.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String name) {
        super("Could not find 'country' with name=" + name);
    }
}