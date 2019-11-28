package com.myperssonal.demo.rest;

public class EntityErrorResponse {
    private int status;
    private String mesage;
    private long timeStamp;

    public EntityErrorResponse() {

    }

    public EntityErrorResponse(int status, String mesage, long timeStamp) {
        super();
        this.status = status;
        this.mesage = mesage;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
