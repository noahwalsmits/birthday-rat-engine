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
        RenderManager.getInstance().addDrawable(new Drawable("/images/testsheet.png", new ScreenArea(0, 0, 100, 100), 9));
        RenderManager.getInstance().addDrawable(new Drawable("/images/engine/missing_image.png", new ScreenArea(0, 0, 100, 100), 10));
        AudioManager.getInstance().playMusic("/sound/Intermission.mp3", true);
    }

    @Override
    public void update(long time) {

    }
}
