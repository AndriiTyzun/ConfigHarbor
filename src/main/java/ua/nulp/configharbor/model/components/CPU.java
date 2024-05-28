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
@Table(name = "cpu")
@NoArgsConstructor
@Getter
@Setter
public class CPU extends Component{
    @Column(name = "cores")
    private int cpuCores;


    @Column(name = "name")
    private String cpuName;

//    @Column(name = "threads")
//    private int cpuThreads;
//
//    @Column(name = "clock")
//    private int cpuClock;

    @Column(name = "socket")
    private String cpuSocket;

//    @Column(name = "process")
//    private int cpuProcess;
//
//    @Column(name = "cache")
    private int cpuCache;

    @Column(name = "tdp")
    private int cpuTDP;

    @Column(name = "performance")
    private int cpuPerformance;

    @OneToOne(mappedBy = "cpu")
    private PCConfiguration pcConfiguration;

    @OneToOne(mappedBy = "cpu")
    private PCUpdatePackage pcUpdatePackage;
}
