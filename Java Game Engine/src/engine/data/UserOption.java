package engine.data;

import java.util.HashMap;
import java.util.Map;

abstract public class UserOption {
    private boolean active;
    private Map<UserOption, OptionDependency> dependencies;

    public UserOption() {
        this.active = true;
        this.dependencies = new HashMap<>();
    }

    public void checkDependencies() {
        boolean cleared = true;
        for (UserOption userOption : this.dependencies.keySet()) {
            if (!this.dependencies.get(userOption).checkDependency(userOption)) {
                cleared = false;
            }
        }
        this.active = cleared;
    }

    public boolean isActive() {
        return active;
    }

    public void addDependency(UserOption userOption, OptionDependency dependency) {
        //TODO throw exception if userOption is not this or anything already in dependencies
        if (userOption.equals(this)) {
            return;
        }
        if (this.dependencies.keySet().contains(userOption)) {
            return;
        }
        this.dependencies.put(userOption, dependency);
    }

    public interface OptionDependency {
        boolean checkDependency(UserOption userOption);
    }
}
