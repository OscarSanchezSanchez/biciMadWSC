package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Station {
    @Id
    private int id;
    private float latitude;
    private float longitude;
    private String name;
    private int light;
    private String number;
    private String address;
    private int activate;
    private int no_available;
    private int total_bases;
    private int dock_bikes;
    private int free_bases;
    private int reservations_count;

    public Station(){};

    public Station(int id, float latitude, float longitude, String name, int light, String number, String address, int activate, int no_available, int total_bases, int dock_bikes, int free_bases, int reservations_count) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.light = light;
        this.number = number;
        this.address = address;
        this.activate = activate;
        this.no_available = no_available;
        this.total_bases = total_bases;
        this.dock_bikes = dock_bikes;
        this.free_bases = free_bases;
        this.reservations_count = reservations_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", light=" + light +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", activate=" + activate +
                ", no_available=" + no_available +
                ", total_bases=" + total_bases +
                ", dock_bikes=" + dock_bikes +
                ", free_bases=" + free_bases +
                ", reservations_count=" + reservations_count +
                '}';
    }
}
