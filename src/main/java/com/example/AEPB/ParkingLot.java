package com.example.AEPB;

import java.util.Date;
import java.util.HashSet;

public class ParkingLot {

    private int stallCount;

    private int usedStallCount;

    private int unusedStallCount;

    private HashSet<String> parkSet = new HashSet<>();

    public ParkingLot(int stallCount, int usedStallCount, int unusedStallCount) {
        this.stallCount = stallCount;
        this.usedStallCount = usedStallCount;
        this.unusedStallCount = unusedStallCount;
    }

    public Car park(Car car) {
        if (unusedStallCount > 0){
            car.setParkSuccess(true);
            car.setParkDate(new Date());
            parkSet.add(car.getPlateNumber());
            usedStallCount++;
            unusedStallCount--;
        }
        return car;
    }

    public boolean take(Ticket ticket) {
        if (isExistSpecifiedCar(ticket.getUniqueFlag())){
            parkSet.remove(ticket.getUniqueFlag());
            return true;
        }
        return false;
    }

    public boolean isExistSpecifiedCar(String flag){
        return parkSet.contains(flag);
    }
}
