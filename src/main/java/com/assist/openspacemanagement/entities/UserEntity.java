package com.assist.openspacemanagement.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

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
    private boolean activated;

    @ManyToOne
    @JoinColumn(name = "role")
    private AuthorityEntity authority;

}
