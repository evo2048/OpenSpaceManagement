package com.assist.openspacemanagement.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private String fristName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column
    private String gender;

    @Column
    private String dateOfBirth;

    @Column
    private String nationality;

    @Column
    private boolean accountEnabled = true;

    @Column
    private boolean accountExpired = false;

    @Column
    private boolean passwordExpired = false;

    @Column
    private boolean accountLocked = false;

    @ManyToOne
    @JoinColumn(name = "authorityId")
    private AuthorityEntity authority;

    @OneToMany
    @JsonIgnore
    private List<OfficeEntity> offices;

    @OneToOne(mappedBy = "userAssigned")
    private DeskEntity deskAssigned;

    @OneToOne(mappedBy = "user")
    private RemoteWorkRequestsEntity remoteWorkRequest;

}
