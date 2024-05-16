package ua.lviv.iot.lab5.service;

import ua.lviv.iot.lab5.domain.Establishment;
import ua.lviv.iot.lab5.domain.ReviewOfEstablishment;

import java.util.List;

public interface ReviewOfEstablishmentService extends GeneralService<ReviewOfEstablishment, Integer> {
    List<Establishment> findEstablishmentByReviewId(Integer id);
}
