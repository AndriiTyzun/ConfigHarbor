package ua.nulp.configharbor.model.components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nulp.configharbor.model.pc_configurations.PCConfiguration;

@Entity
@Table(name = "psu")
@NoArgsConstructor
@Getter
@Setter
public class PSU extends Component{
    @Column(name = "form_factor")
    private String psuFormFactor;

    @Column(name = "wattage")
    private int psuWattage;

    @Column(name = "efficiency_rating")
    private String psuEfficiencyRating;

    @Column(name = "modularity")
    private String psuModularity;

    @OneToOne(mappedBy = "psu")
    private PCConfiguration pcConfiguration;
}
