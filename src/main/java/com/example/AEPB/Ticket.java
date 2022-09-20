package com.example.AEPB;

public class Ticket{

    private String uniqueFlag;

    public Ticket(String plateNumber) {
        this.uniqueFlag = plateNumber;
    }

    public String getUniqueFlag() {
        return uniqueFlag;
    }

    public void setUniqueFlag(String uniqueFlag) {
        this.uniqueFlag = uniqueFlag;
    }
}
