package jsonEntities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

public class JsonDataObjects implements Serializable {
    private IDclass _id;
    private String user_day_code;
    private int idplug_base;
    private TrackClass track;
    private int user_type;
    private int idunplug_base;
    private int travel_time;
    private int idunplug_station;
    private int ageRange;
    private int idplug_station;
    private UnplugTime unplug_hourTime;
    private String zip_code;

    public JsonDataObjects() {
    }

    public IDclass get_id() {
        return _id;
    }

    public void set_id(IDclass _id) {
        this._id = _id;
    }

    public String getUser_day_code() {
        return user_day_code;
    }

    public void setUser_day_code(String user_day_code) {
        this.user_day_code = user_day_code;
    }

    public int getIdplug_base() {
        return idplug_base;
    }

    public void setIdplug_base(int idplug_base) {
        this.idplug_base = idplug_base;
    }

    public TrackClass getTrack() {
        return track;
    }

    public void setTrack(TrackClass track) {
        this.track = track;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public int getIdunplug_base() {
        return idunplug_base;
    }

    public void setIdunplug_base(int idunplug_base) {
        this.idunplug_base = idunplug_base;
    }

    public int getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(int travel_time) {
        this.travel_time = travel_time;
    }

    public int getIdunplug_station() {
        return idunplug_station;
    }

    public void setIdunplug_station(int idunplug_station) {
        this.idunplug_station = idunplug_station;
    }

    public int getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int ageRange) {
        this.ageRange = ageRange;
    }

    public int getIdplug_station() {
        return idplug_station;
    }

    public void setIdplug_station(int idplug_station) {
        this.idplug_station = idplug_station;
    }

    public UnplugTime getUnplugHourTime() {
        return unplug_hourTime;
    }

    public void setUnplugHourTime(UnplugTime unplugHourTime) {
        this.unplug_hourTime = unplugHourTime;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

}
