package ua.nulp.configharbor.model.pc_configurations;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.nulp.configharbor.model.components.*;
import ua.nulp.configharbor.model.users.User;

@Entity
@Table(name = "pc_configuration")
@NoArgsConstructor
@Getter
@Setter
public class PCConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long configurationId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu_id", referencedColumnName = "id")
    private CPU cpu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gpu_id", referencedColumnName = "id")
    private GPU gpu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "motherboard_id", referencedColumnName = "id")
    private Motherboard motherboard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "psu_id", referencedColumnName = "id")
    private PSU psu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ram_id", referencedColumnName = "id")
    private RAM ram;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "update", referencedColumnName = "id")
    private PCUpdatePackage update;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;
}
