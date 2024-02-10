package ua.nulp.configharbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nulp.configharbor.model.components.CPU;

@Repository
public interface CPURepository extends JpaRepository<CPU, Long> {

}
