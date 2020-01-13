package game;

import engine.GameLogic;
import engine.sound.AudioManager;
import engine.visual.RenderManager;
import engine.visual.drawable.Drawable;
import engine.visual.screen.ScreenArea;

public class DemonstrationGameLogic extends GameLogic {

    public DemonstrationGameLogic() {

    }

    @Override
    public void init() {
        Drawable drawable = new Drawable("/images/testsheet.png", new ScreenArea(100, 100, 700, 500), 10);
        drawable.setOnDrawableUpdate(new Drawable.OnDrawableUpdate() {
            @Override
            public void onUpdate(ScreenArea screenArea, double time) {
                //screenArea.move(1, 0);
            }
        });
        RenderManager.getInstance().addDrawable(drawable);

        AudioManager.getInstance().playMusic("/sound/Intermission.mp3", false);
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void stop() {

    }
}
