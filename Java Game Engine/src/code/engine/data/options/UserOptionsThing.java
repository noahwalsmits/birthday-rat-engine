package code.engine.data.options;

public class UserOptionsThing {

    public UserOptionsThing() {
        UserOptionSlider<Integer> slider = new UserOptionSlider<>(0, 0, 100);
        UserOptionToggle toggle = new UserOptionToggle(true);

        slider.addDependency(toggle, new UserOption.OptionDependency() {
            @Override
            public boolean checkDependency(UserOption userOption) {
                if (userOption.isActive()) {
                    if (((UserOptionToggle) userOption).isEnabled()) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void saveToFile() {

    }

}
