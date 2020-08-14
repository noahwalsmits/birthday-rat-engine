package code.engine.data.options;

public class UserOptionToggle extends UserOption {
    private boolean enabled;

    public UserOptionToggle(boolean enabled) {
        super();
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
