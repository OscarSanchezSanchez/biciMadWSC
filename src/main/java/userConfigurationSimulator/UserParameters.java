package userConfigurationSimulator;

public class UserParameters {
    private DestinationPlace destinationPlace;

    public UserParameters(DestinationPlace destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public DestinationPlace getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(DestinationPlace destinationPlace) {
        this.destinationPlace = destinationPlace;
    }
}
