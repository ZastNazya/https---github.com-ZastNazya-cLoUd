package ua.lviv.iot.lab5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.lviv.iot.lab5.exception.*;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityNotFoundHandler(CityNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String countryNotFoundHandler(CountryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CredentialNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String credentialNotFoundHandler(CredentialNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EstablishmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String establishmentNotFoundHandler(EstablishmentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InformationAboutOwnerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String informationAboutOwnerNotFoundHandler(InformationAboutOwnerNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReviewOfEstablishmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String reviewOfEstablishmentNotFoundHandler(ReviewOfEstablishmentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StreetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String streetNotFoundHandler(StreetNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TypeOfEstablishmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String typeOfEstablishmentNotFoundHandler(TypeOfEstablishmentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserAccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userAccountNotFoundHandler(UserAccountNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CityExistForCountryException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String cityExistForCountryHandler(CityExistForCountryException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EstablishmentExistForInformationAboutOwnerException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String establishmentExistForInformationAboutOwnerHandler(EstablishmentExistForInformationAboutOwnerException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EstablishmentExistForStreetException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String establishmentExistForStreetHandler(EstablishmentExistForStreetException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EstablishmentExistForTypeOfEstablishmentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String establishmentExistForTypeOfEstablishmentHandler(EstablishmentExistForTypeOfEstablishmentException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReviewExistForEstablishmentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String reviewExistForEstablishmentHandler(ReviewExistForEstablishmentException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReviewExistForReviewOfEstablishmentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String reviewExistForReviewOfEstablishmentHandler(ReviewExistForReviewOfEstablishmentException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ReviewOfEstablishmentExistForUserAccountException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String reviewOfEstablishmentExistForUserAccountHandler(ReviewOfEstablishmentExistForUserAccountException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StreetExistForCityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String streetExistForCityHandler(StreetExistForCityException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserAccountExistForCredentialException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAccountExistForCredentialHandler(UserAccountExistForCredentialException ex) {
        return ex.getMessage();
    }
}
