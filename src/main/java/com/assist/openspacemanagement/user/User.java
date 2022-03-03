package com.assist.openspacemanagement.user;

import com.assist.openspacemanagement.authority.Authority;
import com.assist.openspacemanagement.desk.Desk;
import com.assist.openspacemanagement.office.Office;
import com.assist.openspacemanagement.remoteWork.RemoteWork;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column
    private String fristName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column
    private String gender;

    @Column
    private String dateOfBirth;

    @Column
    private String nationality;

    @Column
    private boolean accountEnabled = true;

    @Column
    private boolean accountExpired = false;

    @Column
    private boolean passwordExpired = false;

    @Column
    private boolean accountLocked = false;

    @ManyToOne
    @JoinColumn(name = "authorityId")
    private Authority authority;

    @OneToMany
    @JsonIgnore
    private List<Office> offices;

    @OneToOne(mappedBy = "userAssigned")
    private Desk deskAssigned;

    @OneToOne(mappedBy = "user")
    private RemoteWork remoteWorkRequest;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    public Desk getDeskAssigned() {
        return deskAssigned;
    }

    public void setDeskAssigned(Desk deskAssigned) {
        this.deskAssigned = deskAssigned;
    }

    public RemoteWork getRemoteWorkRequest() {
        return remoteWorkRequest;
    }

    public void setRemoteWorkRequest(RemoteWork remoteWorkRequest) {
        this.remoteWorkRequest = remoteWorkRequest;
    }
}
