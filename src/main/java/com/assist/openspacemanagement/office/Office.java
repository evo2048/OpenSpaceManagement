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

    @Column
    private Integer occupiedDeskCount;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "userId")
    private User officeAdmin;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "buildingId")
    private Building building;

    @OneToMany(mappedBy = "officeId")
    private List<Desk> desks;

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getDeskCount() {
        return deskCount;
    }

    public void setDeskCount(Integer deskCount) {
        this.deskCount = deskCount;
    }

    public Integer getUsableDeskCount() {
        return usableDeskCount;
    }

    public void setUsableDeskCount(Integer usableDeskCount) {
        this.usableDeskCount = usableDeskCount;
    }

    public User getOfficeAdmin() {
        return officeAdmin;
    }

    public void setOfficeAdmin(User officeAdmin) {
        this.officeAdmin = officeAdmin;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public List<Desk> getDesks() {
        return desks;
    }

    public void setDesks(List<Desk> desks) {
        this.desks = desks;
    }

    public Integer getOccupiedDeskCount() {
        return occupiedDeskCount;
    }

    public void setOccupiedDeskCount(Integer occupiedDeskCount) {
        this.occupiedDeskCount = occupiedDeskCount;
    }
}
