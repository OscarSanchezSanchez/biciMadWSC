package userConfigurationSimulator;

import java.util.List;

public class UserConfiguration {
    private GeoPosition position;
    private UserType userType;
    private long timeInstant;

    public UserConfiguration(GeoPosition position, UserType userType, long timeInstant) {
        this.position = position;
        this.userType = userType;
        this.timeInstant = timeInstant;
    }

    public GeoPosition getPosition() {
        return position;
    }

    public void setPosition(GeoPosition position) {
        this.position = position;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public long getTimeInstant() {
        return timeInstant;
    }

    public void setTimeInstant(long timeInstant) {
        this.timeInstant = timeInstant;
    }
}
