package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.Establishment;
import ua.lviv.iot.lab5.domain.ReviewOfEstablishment;
import ua.lviv.iot.lab5.exception.ReviewExistForReviewOfEstablishmentException;
import ua.lviv.iot.lab5.exception.ReviewOfEstablishmentNotFoundException;
import ua.lviv.iot.lab5.repository.ReviewOfEstablishmentRepository;
import ua.lviv.iot.lab5.service.ReviewOfEstablishmentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReviewOfEstablishmentServiceImpl implements ReviewOfEstablishmentService {
    private final ReviewOfEstablishmentRepository reviewOfEstablishmentRepository;

    @Autowired
    public ReviewOfEstablishmentServiceImpl(ReviewOfEstablishmentRepository reviewOfEstablishmentRepository) {
        this.reviewOfEstablishmentRepository = reviewOfEstablishmentRepository;
    }

    @Override
    public List<ReviewOfEstablishment> findAll() {
        return reviewOfEstablishmentRepository.findAll();
    }

    @Override
    public ReviewOfEstablishment findById(Integer id) {
        return reviewOfEstablishmentRepository.findById(id)
                .orElseThrow(() -> new ReviewOfEstablishmentNotFoundException(id));
    }

    @Override
    @Transactional
    public ReviewOfEstablishment create(ReviewOfEstablishment reviewOfEstablishment) {
        reviewOfEstablishmentRepository.save(reviewOfEstablishment);
        return reviewOfEstablishment;
    }

    @Override
    @Transactional
    public void update(Integer id, ReviewOfEstablishment uReviewOfEstablishment) {
        ReviewOfEstablishment reviewOfEstablishment = reviewOfEstablishmentRepository.findById(id)
                .orElseThrow(() -> new ReviewOfEstablishmentNotFoundException(id));
        reviewOfEstablishment.setReview(uReviewOfEstablishment.getReview());
        reviewOfEstablishment.setUserAccount(uReviewOfEstablishment.getUserAccount());
        reviewOfEstablishmentRepository.save(reviewOfEstablishment);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        ReviewOfEstablishment reviewOfEstablishment = reviewOfEstablishmentRepository.findById(id)
                .orElseThrow(() -> new ReviewOfEstablishmentNotFoundException(id));
        if (!reviewOfEstablishment.getEstablishments().isEmpty())
            throw new ReviewExistForReviewOfEstablishmentException(id);
        reviewOfEstablishmentRepository.delete(reviewOfEstablishment);
    }

    @Override
    @Transactional
    public List<Establishment> findEstablishmentByReviewId(Integer id) {
        ReviewOfEstablishment reviewOfEstablishment = reviewOfEstablishmentRepository.findById(id)
                .orElseThrow(() -> new ReviewOfEstablishmentNotFoundException(id));
        return reviewOfEstablishment.getEstablishments().stream().toList();
    }
}
