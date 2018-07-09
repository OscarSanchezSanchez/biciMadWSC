package userConfigurationSimulator;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationUsers {
    private List<UserConfiguration> initialUsers;

    public ConfigurationUsers() {
        this.initialUsers = new ArrayList<>();
    }

    public List<UserConfiguration> getInitialUsers() {
        return initialUsers;
    }

    public void setInitialUsers(List<UserConfiguration> initialUsers) {
        this.initialUsers = initialUsers;
    }

    public void addUser(UserConfiguration user){
        this.initialUsers.add(user);
    }
}
