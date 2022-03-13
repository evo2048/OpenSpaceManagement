package com.assist.openspacemanagement.requestDesk;

import javax.persistence.*;

@Entity
@Table(name="requestdesk")
public class RequestDesk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(columnDefinition = "default 'pending'")
    private String status;

    @Column
    private Integer senderId;


    @Column
    private String motivation;

    @Column
    private String responseMotivation;

    @Column()
    private Integer officeId;

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

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }
}
