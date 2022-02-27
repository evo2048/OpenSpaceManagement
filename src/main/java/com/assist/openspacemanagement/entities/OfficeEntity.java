package com.assist.openspacemanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offices")
public class OfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer officeId;

    @Column
    private String officeName;

    @Column
    private Integer floorNumber;

    @Column
    private Integer deskCount;

    @Column
    private Integer usableDeskCount;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity officeAdmin;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    private BuildingEntity building;

    @OneToMany
    @JsonIgnore
    private List<DeskEntity> desks;
}
