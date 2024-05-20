package ua.nulp.configharbor.model.requirements;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_requirements")
@NoArgsConstructor
@Getter
@Setter
public class ApplicationRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long appRequirementsId;

    @Column(name = "cpu")
    private String requirementsCPU;

    @Column(name = "gpu")
    private String requirementsGPU;

    @Column(name = "ram")
    private String requirementsRAM;
}
