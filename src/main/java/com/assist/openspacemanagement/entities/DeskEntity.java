package com.assist.openspacemanagement.entities;

import javax.persistence.*;

@Entity
@Table(name = "desks")
public class DeskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer deskId;

    @ManyToOne
    @JoinColumn(name = "officeId")
    private OfficeEntity officeEntity;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity userAssigned;
}
