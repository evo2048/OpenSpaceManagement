package com.assist.openspacemanagement.repositories;

import com.assist.openspacemanagement.entities.RemoteWorkRequestsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteWorkRequestsRepository extends JpaRepository<RemoteWorkRequestsEntity, Integer> {
}
