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
@Table(name = "authorities")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorityId;

    @Column(length = 15)
    String role;

    @Column
    @OneToMany(mappedBy = "authority")
    @JsonIgnore
    private List<UserEntity> users;

}
