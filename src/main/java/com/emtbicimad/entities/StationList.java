package com.emtbicimad.entities;

import com.emtbicimad.entities.Station;

import java.util.ArrayList;
import java.util.List;

public class StationList {

    private List<Station> stations = new ArrayList<Station>();

    public StationList(){}

    public StationList(List<Station> stations) {
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
    public int getLength(){
        return this.stations.size();
    }
}
