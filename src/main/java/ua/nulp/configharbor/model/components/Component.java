package ua.nulp.configharbor.model.components;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long componentId;

//    @Column(name = "brand")
//    private String componentBrand;

    @Column(name = "price")
    private String componentPrice;
}
