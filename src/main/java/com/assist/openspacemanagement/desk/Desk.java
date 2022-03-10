package com.assist.openspacemanagement.desk;

import com.assist.openspacemanagement.office.Office;
import com.assist.openspacemanagement.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "desks")
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer deskId;

    @ManyToOne
    @JoinColumn(name = "officeId")
    private Office office;

    @OneToOne
    @JoinColumn(name = "userId")
    private User userAssigned;

    public Integer getDeskId() {
        return deskId;
    }

    public void setDeskId(Integer deskId) {
        this.deskId = deskId;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public User getUserAssigned() {
        return userAssigned;
    }

    public void setUserAssigned(User userAssigned) {
        this.userAssigned = userAssigned;
    }
}
