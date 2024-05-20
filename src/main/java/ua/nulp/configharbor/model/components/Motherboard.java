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
@Table(name = "motherboard")
@NoArgsConstructor
@Getter
@Setter
public class Motherboard extends Component{
    @Column(name = "socket")
    private String mbSocket;

    @Column(name = "form_factor")
    private String mbFormFactor;

//    @Column(name = "chipset")
//    private String mbChipset;

    @Column(name = "ram_slots")
    private int mbRAMSlots;

//    @Column(name = "ram_type")
//    private String mbRAMType;
//
//    @Column(name = "usb_slots")
//    private String mbUSBSlots;

    @OneToOne(mappedBy = "motherboard")
    private PCConfiguration pcConfiguration;
}
