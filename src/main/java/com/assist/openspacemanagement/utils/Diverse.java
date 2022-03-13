package com.assist.openspacemanagement.utils;

import com.assist.openspacemanagement.desk.Desk;
import com.assist.openspacemanagement.desk.DeskService;
import com.assist.openspacemanagement.office.Office;
import com.assist.openspacemanagement.office.OfficeService;
import com.assist.openspacemanagement.user.User;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Diverse {
    public static JSONObject statusToJson(Office office){
        JSONObject statusOffice = new JSONObject();
        statusOffice.appendField("officeName",office.getOfficeName());
        statusOffice.appendField("buildingName",office.getBuilding().getBuildingName());
        statusOffice.appendField("floorNumber",office.getFloorNumber());
        statusOffice.appendField("officeAdmin",office.getOfficeAdmin().getFristName() + " "
                                                                + office.getOfficeAdmin().getLastName());
        //add all employee to status for a specific office
        List<String> lstNameOfEmployee = new ArrayList<>();
        office.getDesks().forEach(desk -> {
            lstNameOfEmployee.add(desk.getUserAssigned().getFristName() + " " + desk.getUserAssigned().getLastName());
        });
        statusOffice.appendField("employee", lstNameOfEmployee);

        statusOffice.appendField("totalDesk",office.getDeskCount());
        statusOffice.appendField("usableDesk",office.getUsableDeskCount());
        statusOffice.appendField("occupiedDesk",office.getOccupiedDeskCount());
        statusOffice.appendField("freeDesk",office.getUsableDeskCount() - office.getOccupiedDeskCount());
        //computing percentage for occupation desk
        float percentage = (float)office.getOccupiedDeskCount()/office.getUsableDeskCount();
        statusOffice.appendField("occupationPercentage",percentage* 100+"%");

        return statusOffice;
    }
    public static JSONObject userToStatus(User user){
        JSONObject jsonObject = new JSONObject();

        jsonObject.appendField("FirstName",user.getFristName());
        jsonObject.appendField("LastName",user.getLastName());

        Desk desk = DeskService.deskRepository.searchForExistingDesk(user.getUserId());
        Office office;
        if(desk != null){
            office = OfficeService.getOfficeRepository().getById(desk.getOfficeId());
            if(office != null) {
                jsonObject.appendField("Building", office.getBuilding().getBuildingName());
                jsonObject.appendField("Office", office.getOfficeName());
            }
        }
        else {
            jsonObject.appendField("Building", "none");
            jsonObject.appendField("Office", "none");
        }

        jsonObject.appendField("RemoteWork",user.getRemoteWorkPercentage());

        return jsonObject;
    }
}
