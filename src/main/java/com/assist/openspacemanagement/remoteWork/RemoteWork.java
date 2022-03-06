package com.assist.openspacemanagement.remoteWork;

import com.assist.openspacemanagement.user.User;
import javax.persistence.*;

@Entity
public class RemoteWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column
    String status;

    @Column
    private Integer senderId;

    @Column
    private Integer receiveId;
}
