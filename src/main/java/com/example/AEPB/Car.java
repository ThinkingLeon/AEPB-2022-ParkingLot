package com.example.AEPB;

import java.util.Date;

public class Car {

    private String plateNumber;

    private boolean isPark;

    private Date parkDate;

    public Car(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public boolean isPark() {
        return isPark;
    }

    public void setParkSuccess(boolean flag) {
        isPark = flag;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public Date getParkDate() {
        return parkDate;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setParkDate(Date parkDate) {
        this.parkDate = parkDate;
    }
}
