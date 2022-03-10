package com.assist.openspacemanagement.building;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building,Integer>{
    @Modifying
    @Query(value="select desks.user_id from Buildings " +
            "INNER JOIN Offices on :id = Offices.building_id " +
            "LEFT JOIN Desks on Offices.office_id = Desks.office_id",nativeQuery = true)
    List<Integer> getUserAssigned(int id);

}
