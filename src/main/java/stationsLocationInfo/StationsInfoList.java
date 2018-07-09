package stationsLocationInfo;

import com.google.gson.annotations.Expose;

import java.util.List;

public class StationsInfoList {
    @Expose
    private List<StationInfo> stations;

    public List<StationInfo> getStations() {
        return stations;
    }

    public void setStations(List<StationInfo> stations) {
        this.stations = stations;
    }

    public void addBikes(int num_bikes){
        List<StationInfo> stations = this.getStations();
        for (StationInfo station: stations){
            station.setBikes(num_bikes);
        }
    }

    public void modifyCapacity(int capacity){
        List<StationInfo> stations = this.getStations();
        for (StationInfo station: stations){
            station.setCapacity(capacity);
        }
    }
}
