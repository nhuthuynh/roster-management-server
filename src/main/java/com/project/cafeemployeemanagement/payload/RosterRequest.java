package com.project.cafeemployeemanagement.payload;

import java.util.ArrayList;
import java.util.List;

public class RosterRequest extends RosterPayload {

    public RosterRequest() {
        super();
    }

    private List<ShiftRequest> shiftList = new ArrayList<>();

    public List<ShiftRequest> getShiftList() {
        return shiftList;
    }

    public void setShiftList(List<ShiftRequest> shiftList) {
        this.shiftList = shiftList;
    }

}
