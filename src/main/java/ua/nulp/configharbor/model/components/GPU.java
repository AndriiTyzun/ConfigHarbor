package ua.nulp.configharbor.model.components;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nulp.configharbor.model.pc_configurations.PCConfiguration;
import ua.nulp.configharbor.model.pc_configurations.PCUpdatePackage;

@Entity
@Table(name = "gpu")
@NoArgsConstructor
@Getter
@Setter
public class GPU extends Component{
    @Column(name = "chip")
    private String gpuChip;

    @Column(name = "clock")
    private String gpuClock;

    @Column(name = "bus_type")
    private String gpuBusType;

    @Column(name = "bus_width")
    private String gpuBusWidth;

    @Column(name = "memory_type")
    private int gpuMemoryType;

    @Column(name = "memory_size")
    private int gpuMemorySize;

    @Column(name = "memory_clock")
    private String gpuMemoryClock;

    @Column(name = "shaders_tmu_rop")
    private String gpuSTR;

    @Column(name = "performance")
    private int gpuPerformance;

    @OneToOne(mappedBy = "gpu")
    private PCConfiguration pcConfiguration;

    @OneToOne(mappedBy = "gpu")
    private PCUpdatePackage pcUpdatePackage;
}
