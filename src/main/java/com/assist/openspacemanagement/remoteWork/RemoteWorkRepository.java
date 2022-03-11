package com.assist.openspacemanagement.remoteWork;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RemoteWorkRepository extends JpaRepository<RemoteWork,Integer> {
    @Query(value = "select * from remote where sender_id = :sender",nativeQuery = true)
    RemoteWork findBySenderId(int sender);

    @Query(value = "select * from remote where status = 'pending'",nativeQuery = true)
    List<RemoteWork> findByStatus();
}
