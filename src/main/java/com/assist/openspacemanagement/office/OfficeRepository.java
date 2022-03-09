package com.assist.openspacemanagement.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office,Integer> {
    @Query(value="select * from offices where building_id = :id",nativeQuery = true)
    List<Office> getAllOfficeFromBuilding(int id);

    @Modifying
    @Transactional
    @Query(value="delete from offices where office_id = :id",nativeQuery = true)
    void removeOfficeFromDatabase(int id);

    @Query(value="select desks.user_id from Offices " +
            "LEFT JOIN Desks on :id = Desks.office_id",nativeQuery = true)
    List<Integer> getAllUserAssignedOffice(int id);
}
