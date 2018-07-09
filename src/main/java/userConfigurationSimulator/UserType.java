package userConfigurationSimulator;

import java.util.List;

public class UserType {
    private String typeName;
    private UserParameters parameters;

    public UserType(String typeName, UserParameters parameters) {
        this.typeName = typeName;
        this.parameters = parameters;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public UserParameters getParameters() {
        return parameters;
    }

    public void setParameters(UserParameters parameters) {
        this.parameters = parameters;
    }
}
