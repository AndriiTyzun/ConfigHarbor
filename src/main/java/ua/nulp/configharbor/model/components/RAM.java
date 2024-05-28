package ua.nulp.configharbor.model.components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nulp.configharbor.model.pc_configurations.PCConfiguration;
import ua.nulp.configharbor.model.pc_configurations.PCUpdatePackage;

@Entity
@Table(name = "ram")
@NoArgsConstructor
@Getter
@Setter
public class RAM extends Component{
    @Column(name = "name")
    private String ramName;

    @Column(name = "total_size")
    private int ramTotalSize;

    @Column(name = "number_of_planks")
    private int ramNumberOfPlanks;

//    @Column(name = "type")
//    private String ramType;
//
//    @Column(name = "frequency")
//    private int ramFrequency;

    @OneToOne(mappedBy = "ram")
    private PCConfiguration pcConfiguration;

    @OneToOne(mappedBy = "ram")
    private PCUpdatePackage pcUpdatePackage;
}
