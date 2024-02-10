package ua.nulp.configharbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nulp.configharbor.model.components.Motherboard;

@Repository
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {

}
