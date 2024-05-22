package ua.nulp.configharbor.model.pc_configurations;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PCRequirements {
    private String pcType;
    private double pcPrice;
    private boolean pcUpgrade;
}
