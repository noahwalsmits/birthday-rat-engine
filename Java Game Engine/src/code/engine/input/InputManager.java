package code.engine.input;

import javafx.animation.AnimationTimer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InputManager {
    private Queue<KeyboardInput> inputQueue;
    private static int maxWait = 10;

    public InputManager() {
        this.inputQueue = new ConcurrentLinkedQueue<>();
    }

    public void start() {
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                //update(last)
                last = now;
                //draw()
            }
        }.start();
    }
}
