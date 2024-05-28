package ua.nulp.configharbor.service;

import ua.nulp.configharbor.model.pc_configurations.PCConfiguration;
import ua.nulp.configharbor.model.users.User;

import java.util.List;

public interface ConfigService {
    void addConfiguration(PCConfiguration configuration);
    PCConfiguration getConfigurationByID(long id);
    List<PCConfiguration> getConfigurationByUser(User user);
}
