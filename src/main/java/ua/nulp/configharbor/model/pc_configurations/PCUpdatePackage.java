package ua.nulp.configharbor.model.pc_configurations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nulp.configharbor.model.components.CPU;
import ua.nulp.configharbor.model.components.GPU;
import ua.nulp.configharbor.model.components.RAM;

@Entity
@Table(name = "update")
@NoArgsConstructor
@Getter
@Setter
public class PCUpdatePackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long updateId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu_id", referencedColumnName = "id")
    private CPU cpu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gpu_id", referencedColumnName = "id")
    private GPU gpu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ram_id", referencedColumnName = "id")
    private RAM ram;

//    @OneToOne(mappedBy = "update")
//    private PCConfiguration pcConfiguration;
}
