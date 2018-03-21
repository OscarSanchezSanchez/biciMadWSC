package com.emtbicimad.csvutils;

public class CSVstation {

    private String date;
    private String hour;
    private boolean weekend_event;
    private int id_station;
    private int light;
    private int activate;
    private int no_available;
    private int total_bases;
    private int dock_bikes;
    private int free_bases;
    private int reservations_count;

    public CSVstation(String date, String hour,int id_station, boolean weekend_event, int light, int activate, int no_available, int total_bases, int dock_bikes, int free_bases, int reservations_count) {
        this.date = date;
        this.hour = hour;
        this.id_station = id_station;
        this.weekend_event = weekend_event;
        this.light = light;
        this.activate = activate;
        this.no_available = no_available;
        this.total_bases = total_bases;
        this.dock_bikes = dock_bikes;
        this.free_bases = free_bases;
        this.reservations_count = reservations_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean getWeek_event() {
        return weekend_event;
    }

    public void setWeek_event(boolean week_event) {
        this.weekend_event = week_event;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getActivate() {
        return activate;
    }

    public void setActivate(int activate) {
        this.activate = activate;
    }

    public int getNo_available() {
        return no_available;
    }

    public void setNo_available(int no_available) {
        this.no_available = no_available;
    }

    public int getTotal_bases() {
        return total_bases;
    }

    public void setTotal_bases(int total_bases) {
        this.total_bases = total_bases;
    }

    public int getDock_bikes() {
        return dock_bikes;
    }

    public void setDock_bikes(int dock_bikes) {
        this.dock_bikes = dock_bikes;
    }

    public int getFree_bases() {
        return free_bases;
    }

    public void setFree_bases(int free_bases) {
        this.free_bases = free_bases;
    }

    public int getReservations_count() {
        return reservations_count;
    }

    public void setReservations_count(int reservations_count) {
        this.reservations_count = reservations_count;
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }
}
