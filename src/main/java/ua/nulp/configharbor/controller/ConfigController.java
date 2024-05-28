package ua.nulp.configharbor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ua.nulp.configharbor.model.dto.PCConfigurationDTO;
import ua.nulp.configharbor.model.pc_configurations.PCConfiguration;
import ua.nulp.configharbor.model.pc_configurations.PCRequirements;

@RestController
@RequestMapping("/api/config")
public class ConfigController {

    private final RestTemplate restTemplate;

    public ConfigController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/getPCConfig")
    public PCConfigurationDTO getPCConfig(@RequestBody PCRequirements requirements) {
        String url = "http://127.0.0.1:8000/config";

//        requirements = new PCRequirements();
//        requirements.setPcType("Gaming");
//        requirements.setPcPrice(2000);
//        requirements.setPcUpgrade(false);
        ResponseEntity<PCConfigurationDTO> response = restTemplate.postForEntity(url, requirements, PCConfigurationDTO.class);
        return response.getBody();
    }
}
