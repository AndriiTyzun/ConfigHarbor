package ua.nulp.configharbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nulp.configharbor.model.components.RAM;

@Repository
public interface RAMRepository extends JpaRepository<RAM, Long> {
    public RAM findRAMByRamName(String ramName);
}
