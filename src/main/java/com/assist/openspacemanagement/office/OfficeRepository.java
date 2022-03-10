package com.assist.openspacemanagement.office;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office,Integer> {
    @Query(value="select desks.user_id from Offices " +
            "LEFT JOIN Desks on :id = Desks.office_id",nativeQuery = true)
    List<Integer> getAllUserAssignedOffice(int id);

    @Query(value = "select desks.office_id from Offices LEFT JOIN Desks On Offices.office_id = Desks.office_id " +
            "where Desks.user_id = :user_id",nativeQuery = true)
    Integer findIdOfficeForUser(int user_id);
}
