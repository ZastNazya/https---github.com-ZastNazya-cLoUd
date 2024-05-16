package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.TypeOfEstablishment;
import ua.lviv.iot.lab5.exception.EstablishmentExistForTypeOfEstablishmentException;
import ua.lviv.iot.lab5.exception.TypeOfEstablishmentNotFoundException;
import ua.lviv.iot.lab5.repository.TypeOfEstablishmentRepository;
import ua.lviv.iot.lab5.service.TypeOfEstablishmentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TypeOfEstablishmentServiceImpl implements TypeOfEstablishmentService {
    private final TypeOfEstablishmentRepository typeOfEstablishmentRepository;

    @Autowired
    public TypeOfEstablishmentServiceImpl(TypeOfEstablishmentRepository typeOfEstablishmentRepository) {
        this.typeOfEstablishmentRepository = typeOfEstablishmentRepository;
    }

    @Override
    public List<TypeOfEstablishment> findAll() {
        return typeOfEstablishmentRepository.findAll();
    }

    @Override
    public TypeOfEstablishment findById(Integer id) {
        return typeOfEstablishmentRepository.findById(id)
                .orElseThrow(() -> new TypeOfEstablishmentNotFoundException(id));
    }

    @Override
    @Transactional
    public TypeOfEstablishment create(TypeOfEstablishment typeOfEstablishment) {
        typeOfEstablishmentRepository.save(typeOfEstablishment);
        return typeOfEstablishment;
    }

    @Override
    @Transactional
    public void update(Integer id, TypeOfEstablishment uTypeOfEstablishment) {
        TypeOfEstablishment typeOfEstablishment = typeOfEstablishmentRepository.findById(id)
                .orElseThrow(() -> new TypeOfEstablishmentNotFoundException(id));
        typeOfEstablishment.setTypeName(uTypeOfEstablishment.getTypeName());
        typeOfEstablishmentRepository.save(typeOfEstablishment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        TypeOfEstablishment typeOfEstablishment = typeOfEstablishmentRepository.findById(id)
                .orElseThrow(() -> new TypeOfEstablishmentNotFoundException(id));
        if (!typeOfEstablishment.getEstablishments().isEmpty())
            throw new EstablishmentExistForTypeOfEstablishmentException(id);
        typeOfEstablishmentRepository.delete(typeOfEstablishment);
    }
}
