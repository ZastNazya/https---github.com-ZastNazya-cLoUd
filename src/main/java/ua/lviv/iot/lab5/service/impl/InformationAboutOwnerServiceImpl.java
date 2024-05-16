package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.InformationAboutOwner;
import ua.lviv.iot.lab5.exception.EstablishmentExistForInformationAboutOwnerException;
import ua.lviv.iot.lab5.exception.InformationAboutOwnerNotFoundException;
import ua.lviv.iot.lab5.repository.InformationAboutOwnerRepository;
import ua.lviv.iot.lab5.service.InformationAboutOwnerService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class InformationAboutOwnerServiceImpl implements InformationAboutOwnerService {
    private final InformationAboutOwnerRepository informationAboutOwnerRepository;

    @Autowired
    public InformationAboutOwnerServiceImpl(InformationAboutOwnerRepository informationAboutOwnerRepository) {
        this.informationAboutOwnerRepository = informationAboutOwnerRepository;
    }

    @Override
    public List<InformationAboutOwner> findAll() {
        return informationAboutOwnerRepository.findAll();
    }

    @Override
    public InformationAboutOwner findById(Integer id) {
        return informationAboutOwnerRepository.findById(id)
                .orElseThrow(() -> new InformationAboutOwnerNotFoundException(id));
    }

    @Override
    @Transactional
    public InformationAboutOwner create(InformationAboutOwner informationAboutOwner) {
        informationAboutOwnerRepository.save(informationAboutOwner);
        return informationAboutOwner;
    }

    @Override
    @Transactional
    public void update(Integer id, InformationAboutOwner uInformationAboutOwner) {
        InformationAboutOwner informationAboutOwner = informationAboutOwnerRepository.findById(id)
                .orElseThrow(() -> new InformationAboutOwnerNotFoundException(id));
        informationAboutOwner.setName(uInformationAboutOwner.getName());
        informationAboutOwner.setSurname(uInformationAboutOwner.getSurname());
        informationAboutOwner.setAge(uInformationAboutOwner.getAge());
        informationAboutOwner.setFortunes(uInformationAboutOwner.getFortunes());
        informationAboutOwnerRepository.save(informationAboutOwner);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        InformationAboutOwner informationAboutOwner = informationAboutOwnerRepository.findById(id)
                .orElseThrow(() -> new InformationAboutOwnerNotFoundException(id));
        if (!informationAboutOwner.getEstablishments().isEmpty())
            throw new EstablishmentExistForInformationAboutOwnerException(id);

    }


    @Override
    @Transactional
    public InformationAboutOwner inputDataAboutOwner(String name, String surname, Integer age, Integer fortunes) {
        InformationAboutOwner informationAboutOwner = new InformationAboutOwner(name, surname, age, fortunes);
        informationAboutOwnerRepository.save(informationAboutOwner);
        return informationAboutOwner;
    }
}
