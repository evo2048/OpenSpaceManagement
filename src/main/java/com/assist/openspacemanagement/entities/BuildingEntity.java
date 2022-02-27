package com.assist.openspacemanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "buildings")
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buildingId;

    @Column
    private String buildingName;

    @Column
    private Integer floorCount;

    @Column
    private String address;

    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<OfficeEntity> officeList;
}
