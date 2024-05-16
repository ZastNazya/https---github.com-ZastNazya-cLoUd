package ua.lviv.iot.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.lab5.domain.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Integer> {
}
