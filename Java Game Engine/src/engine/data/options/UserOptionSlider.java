package engine.data.options;

public class UserOptionSlider<E extends Number> extends UserOption {
    private E value;
    private E minimum;
    private E maximum;

    public UserOptionSlider(E value, E minimum, E maximum) {
        super();
        this.value = value;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public E getValue() {
        return value;
    }

    public E getMinimum() {
        return minimum;
    }

    public E getMaximum() {
        return maximum;
    }

    public void setValue(E value) {
        //TODO compare value to minimum and maximum
        this.value = value;
    }
}
