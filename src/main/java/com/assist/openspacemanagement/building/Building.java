package com.assist.openspacemanagement.building;

import com.assist.openspacemanagement.office.Office;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buildingId;

    @Column
    private String buildingName;

    @Column
    private Integer floorCount;

    @Column
    private String address;

    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<Office> officeList;
}
