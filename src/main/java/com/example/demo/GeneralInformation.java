package com.example.demo;

import java.io.Serializable;

public class GeneralInformation implements Serializable{

    private String time;
    private int code;
    private String description;
    private String whoAmI;
    private String version;
    private StationList data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhoAmI() {
        return whoAmI;
    }

    public void setWhoAmI(String whoAmI) {
        this.whoAmI = whoAmI;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public StationList getData() {
        return data;
    }

    public void setData(StationList data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GeneralInformation{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", whoAmI='" + whoAmI + '\'' +
                ", version='" + version + '\'' +
                ", data=" + data.toString() +
                '}';
    }
}
