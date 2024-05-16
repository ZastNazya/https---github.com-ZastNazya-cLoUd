package ua.lviv.iot.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.lab5.domain.Establishment;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {
    @Procedure("dymanic_procedure")
    void dymanicProcedure();
}
