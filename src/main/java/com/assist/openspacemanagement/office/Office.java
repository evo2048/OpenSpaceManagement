package com.assist.openspacemanagement.office;

import com.assist.openspacemanagement.building.Building;
import com.assist.openspacemanagement.desk.Desk;
import com.assist.openspacemanagement.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offices")
public class Office {
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
    private User officeAdmin;

    @ManyToOne
    @JoinColumn(name = "buildingId")
    private Building building;

    @OneToMany
    @JsonIgnore
    private List<Desk> desks;
}
