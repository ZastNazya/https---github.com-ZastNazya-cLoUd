package ua.lviv.iot.lab5.service;

import ua.lviv.iot.lab5.domain.InformationAboutOwner;

public interface InformationAboutOwnerService extends GeneralService<InformationAboutOwner, Integer> {
    InformationAboutOwner inputDataAboutOwner(String name, String surname, Integer age, Integer fortunes);
}
