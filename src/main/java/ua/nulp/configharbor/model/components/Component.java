package ua.nulp.configharbor.model.components;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long componentId;


    @Column(name = "price")
    private String componentPrice;
}

//    @Column(name = "brand")
//    private String componentBrand;