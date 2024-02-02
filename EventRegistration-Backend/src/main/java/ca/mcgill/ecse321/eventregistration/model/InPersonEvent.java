package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;

@Entity
public class InPersonEvent extends Event {

    private String address;

    // ------------------------
    // CONSTRUCTOR
    // ------------------------

    public InPersonEvent(int aId, String aName, Date aDate, Time aStartTime, Time aEndTime, int aRegistrationLimit,
            String aAddress) {
        super(aId, aName, aDate, aStartTime, aEndTime, aRegistrationLimit);
        address = aAddress;
    }

    // ------------------------
    // INTERFACE
    // ------------------------

    public boolean setAddress(String aAddress) {
        boolean wasSet = false;
        address = aAddress;
        wasSet = true;
        return wasSet;
    }

    public String getAddress() {
        return address;
    }

    public void delete() {
        super.delete();
    }

    public String toString() {
        return super.toString() + "[" +
                "address" + ":" + getAddress() + "]";
    }
}