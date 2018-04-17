package jsonEntities;

import java.util.List;

public class TrackClass {
    private String type;
    private List<FeatureDescription> features;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FeatureDescription> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDescription> features) {
        this.features = features;
    }
}
