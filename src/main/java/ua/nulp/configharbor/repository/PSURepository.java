package ua.nulp.configharbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nulp.configharbor.model.components.PSU;

@Repository
public interface PSURepository extends JpaRepository<PSU, Long> {

}
