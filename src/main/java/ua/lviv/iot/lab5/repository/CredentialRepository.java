package ua.lviv.iot.lab5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.lab5.domain.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    @Procedure("insert_10_credential")
    void insert10Credential();
}
