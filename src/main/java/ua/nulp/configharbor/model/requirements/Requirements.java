package ua.nulp.configharbor.model.requirements;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "requirements")
@NoArgsConstructor
@Getter
@Setter
public class Requirements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requirementsId;

    @Column(name = "cpu")
    private String requirementsCPU;

    @Column(name = "gpu")
    private String requirementsGPU;

    @Column(name = "motherboard")
    private String requirementsMotherboard;

    @Column(name = "ram")
    private String requirementsRAM;

    @Column(name = "psu")
    private String requirementsPSU;
}
