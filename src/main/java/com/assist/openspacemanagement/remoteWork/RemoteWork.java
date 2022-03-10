package com.assist.openspacemanagement.remoteWork;

import com.assist.openspacemanagement.user.User;
import javax.persistence.*;

@Entity
@Table(name = "remote")
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

    @Column
    private String motivation;

    @Column
    private String responseMotivation;

    @Column
    private Integer percentage;


    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }


    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getResponseMotivation() {
        return responseMotivation;
    }

    public void setResponseMotivation(String responseMotivation) {
        this.responseMotivation = responseMotivation;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
