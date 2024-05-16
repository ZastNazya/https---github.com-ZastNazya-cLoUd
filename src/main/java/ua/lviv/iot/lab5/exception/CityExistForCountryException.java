package ua.lviv.iot.lab5.exception;

public class CityExistForCountryException extends RuntimeException{
    public CityExistForCountryException(String name) {
        super("There are available cities for 'country' with id=" + name);
    }
}
