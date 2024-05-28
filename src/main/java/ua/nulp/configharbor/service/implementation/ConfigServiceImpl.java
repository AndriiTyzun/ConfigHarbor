package ua.nulp.configharbor.service.implementation;

import org.springframework.stereotype.Service;
import ua.nulp.configharbor.model.pc_configurations.PCConfiguration;
import ua.nulp.configharbor.model.users.User;
import ua.nulp.configharbor.repository.ConfigRepository;
import ua.nulp.configharbor.service.ConfigService;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {
    private final ConfigRepository configRepository;

    public ConfigServiceImpl(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @Override
    public void addConfiguration(PCConfiguration configuration) {
        configRepository.save(configuration);
    }

    @Override
    public PCConfiguration getConfigurationByID(long id) {
        return configRepository.findPCConfigurationByConfigurationId(id);
    }

    @Override
    public List<PCConfiguration> getConfigurationByUser(User user) {
        return configRepository.findPCConfigurationsByUserId(user);
    }
}
