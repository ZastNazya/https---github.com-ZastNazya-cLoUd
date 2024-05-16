package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.Street;
import ua.lviv.iot.lab5.exception.EstablishmentExistForStreetException;
import ua.lviv.iot.lab5.exception.StreetNotFoundException;
import ua.lviv.iot.lab5.repository.StreetRepository;
import ua.lviv.iot.lab5.service.StreetService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;

    @Autowired
    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    @Override
    public List<Street> findAll() {
        return streetRepository.findAll();
    }

    @Override
    public Street findById(Integer id) {
        return streetRepository.findById(id)
                .orElseThrow(() -> new StreetNotFoundException(id));
    }

    @Override
    @Transactional
    public Street create(Street street) {
        streetRepository.save(street);
        return street;
    }

    @Override
    @Transactional
    public void update(Integer id, Street uStreet) {
        Street street =  streetRepository.findById(id)
                .orElseThrow(() -> new StreetNotFoundException(id));
        street.setCity(uStreet.getCity());
        street.setName(uStreet.getName());
        streetRepository.save(street);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Street street =  streetRepository.findById(id)
                .orElseThrow(() -> new StreetNotFoundException(id));
        if(!street.getEstablishments().isEmpty()) throw new EstablishmentExistForStreetException(id);
        streetRepository.delete(street);
    }
}
