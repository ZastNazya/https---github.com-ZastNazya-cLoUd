package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.Establishment;
import ua.lviv.iot.lab5.domain.ReviewOfEstablishment;
import ua.lviv.iot.lab5.exception.EstablishmentNotFoundException;
import ua.lviv.iot.lab5.exception.ReviewExistForEstablishmentException;
import ua.lviv.iot.lab5.repository.EstablishmentRepository;
import ua.lviv.iot.lab5.service.EstablishmentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EstablishmentServiceImpl implements EstablishmentService {
    private final EstablishmentRepository establishmentRepository;

    @Autowired
    public EstablishmentServiceImpl(EstablishmentRepository establishmentRepository) {
        this.establishmentRepository = establishmentRepository;
    }

    @Override
    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    @Override
    public Establishment findById(Integer id) {
        return establishmentRepository.findById(id)
                .orElseThrow(() -> new EstablishmentNotFoundException(id));
    }

    @Override
    @Transactional
    public Establishment create(Establishment establishment) {
        establishmentRepository.save(establishment);
        return establishment;
    }

    @Override
    @Transactional
    public void update(Integer id, Establishment uEstablishment) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new EstablishmentNotFoundException(id));
        establishment.setName(uEstablishment.getName());
        establishment.setRating(uEstablishment.getRating());
        establishment.setTypeOfEstablishment(uEstablishment.getTypeOfEstablishment());
        establishment.setStreet(uEstablishment.getStreet());
        establishment.setInformationAboutOwner(uEstablishment.getInformationAboutOwner());
        establishmentRepository.save(establishment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new EstablishmentNotFoundException(id));
        if (!establishment.getReviews().isEmpty()) throw new ReviewExistForEstablishmentException(id);
        establishmentRepository.delete(establishment);
        establishmentRepository.delete(establishment);
    }

    @Override
    @Transactional
    public List<ReviewOfEstablishment> findReviewByEstablishmentId(Integer id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new EstablishmentNotFoundException(id));
        return establishment.getReviews().stream().toList();
    }

    @Override
    @Transactional
    public void dymanicProcedure() {
        establishmentRepository.dymanicProcedure();
    }
}
