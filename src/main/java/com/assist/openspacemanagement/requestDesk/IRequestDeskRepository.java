package com.assist.openspacemanagement.requestDesk;

import com.assist.openspacemanagement.remoteWork.RemoteWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRequestDeskRepository extends JpaRepository<RequestDesk,Integer> {
    @Query(value = "select * from requestdesk where status = 'pending'",nativeQuery = true)
    List<RequestDesk> findByStatus();
    @Query(value = "select * from requestdesk where sender_id = :sender",nativeQuery = true)
    RequestDesk findBySenderId(int sender);
}
