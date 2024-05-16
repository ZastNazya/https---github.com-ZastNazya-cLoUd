package ua.lviv.iot.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.lab5.domain.InformationAboutOwner;

@Repository
public interface InformationAboutOwnerRepository extends JpaRepository<InformationAboutOwner, Integer> {
    @Procedure("input_data_about_owner")
    InformationAboutOwner inputDataAboutOwner(String name, String surname, Integer age, Integer fortunes);
}
