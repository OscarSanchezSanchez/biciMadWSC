package jsonEntities;

public class FeatureDescription {
    private Geometry geometry;
    private PropertiesFeature properties;
    private String type;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public PropertiesFeature getProperties() {
        return properties;
    }

    public void setProperties(PropertiesFeature properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
