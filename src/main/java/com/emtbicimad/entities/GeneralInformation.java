package com.emtbicimad.entities;

import javafx.util.converter.DateTimeStringConverter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Document
public class GeneralInformation implements Serializable{

    @DateTimeFormat(style="dd-MM-yyyy HH:mm:ss.SSS")
    private Date time;
    private StationList data;

    public GeneralInformation(String time) {
        this.setTime(time);
    }
    public GeneralInformation() {}

    public Date getTime() {
        return time;
    }

    public void setTime(String time) {
        DateTimeStringConverter x = new DateTimeStringConverter("dd-MM-yyyy HH:mm:ss.SSS");
        this.time = x.fromString(time);
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
                ", data=" + data.toString() +
                '}';
    }
}
