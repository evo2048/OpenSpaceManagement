package com.assist.openspacemanagement.desk;

import com.assist.openspacemanagement.office.Office;
import com.assist.openspacemanagement.user.User;

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
}
