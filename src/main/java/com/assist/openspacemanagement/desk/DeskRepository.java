package com.assist.openspacemanagement.desk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DeskRepository extends JpaRepository<Desk,Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete  from desks where office_id = :id",nativeQuery = true)
    void deleteWhereOfficeId(int id);

    @Query(value = "select * from desks where user_id = :userId",nativeQuery = true)
    Desk searchForExistingDesk(int userId);


}
