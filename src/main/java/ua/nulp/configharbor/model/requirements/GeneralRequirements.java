package ua.nulp.configharbor.model.requirements;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "general_requirements")
@NoArgsConstructor
@Getter
@Setter
public class GeneralRequirements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long genRequirementsId;
}
