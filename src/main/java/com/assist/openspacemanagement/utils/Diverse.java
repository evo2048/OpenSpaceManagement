package com.assist.openspacemanagement.utils;

import com.assist.openspacemanagement.authority.Authority;
import com.assist.openspacemanagement.user.User;
import net.minidev.json.JSONObject;


public class Diverse {
    public static User jsonToUser(JSONObject jsonObject){
        User user = new User();
        user.setFristName(jsonObject.getAsString("firstName"));
        user.setLastName(jsonObject.getAsString("lastName"));
        user.setEmail(jsonObject.getAsString("email"));
        user.setPassword(jsonObject.getAsString("password"));

        String role = jsonObject.getAsString("role");

        int val = 0;

        if(role.equals("OFFICE-ADMIN"))
            val = 2;
        else if (role.equals("EMPLOYEE"))
            val = 3;
        else
            val = 1;

        Authority authority = new Authority();
        authority.setAuthorityId(val);
        user.setAuthority(authority);

        user.setGender(jsonObject.getAsString("gender"));
        user.setDateOfBirth(jsonObject.getAsString("date-of-birth"));
        user.setNationality(jsonObject.getAsString("nationality"));

        user.setAccountEnabled(true);
        user.setDeskAssigned(null);
        user.setOffices(null);
        return user;
    }
}
