package ua.nulp.configharbor.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nulp.configharbor.model.components.*;
import ua.nulp.configharbor.model.pc_configurations.PCUpdatePackage;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PCConfigurationDTO {
    private String cpu;

    private String gpu;

    private String motherboard;

    private String psu;

    private String ram;

    private String pcType;

    private double pcPrice;
}
