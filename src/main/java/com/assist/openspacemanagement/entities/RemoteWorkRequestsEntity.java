package com.assist.openspacemanagement.entities;

import javax.persistence.*;

@Entity
public class RemoteWorkRequestsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column
    String status;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
}
