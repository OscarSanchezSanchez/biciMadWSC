package stationsLocationInfo;

import com.google.gson.annotations.Expose;
import userConfigurationSimulator.GeoPosition;

public class StationInfo {
    private int id;
    @Expose
    private int capacity;
    @Expose
    private GeoPosition position;
    @Expose
    private int bikes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public GeoPosition getPosition() {
        return position;
    }

    public int getBikes() {
        return bikes;
    }

    public void setBikes(int bikes) {
        this.bikes = bikes;
    }

    public void setPosition(GeoPosition position) {
        this.position = position;
    }
}
