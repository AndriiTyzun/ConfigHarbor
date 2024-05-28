package ua.nulp.configharbor.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.nulp.configharbor.model.pc_configurations.PCConfiguration;
import ua.nulp.configharbor.model.users.User;

import java.util.List;

@Registered
public interface ConfigRepository extends JpaRepository<PCConfiguration, Long> {
    public PCConfiguration findPCConfigurationByConfigurationId(long configurationId);
    public List<PCConfiguration> findPCConfigurationsByUserId(User user);
}
