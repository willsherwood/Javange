package sherwood.gameScreen;

import sherwood.gameScreen.map.Mapping;
import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.screenStates.ScreenState;

import java.awt.*;

public class FPSUpdateAlgorithm implements UpdateAlgorithm {

    private Mapping map;
    private double time;
    private double weight = 0.1;
    private long t1, t2;
    private final int fps;

    public FPSUpdateAlgorithm (int fps) {
        this.fps = fps;
    }

    public FPSUpdateAlgorithm() {
        this(GameScreen.DEFAULT_TICKSPERSEC);
    }

    public void setMap(Mapping map) {
        this.map = map;
    }

    public void requestNewMapping (Mapping map) {
        this.map = map;
    }

    @Override
    public void update (ScreenState screenState, Graphics2D graphics,
                        GameScreen gameScreen, KeyboardInput input) {
        t1 = System.currentTimeMillis();
        screenState.step(input.keys());
        screenState.draw(graphics);
        gameScreen.paintToBuffer(map);
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("FPS: %.1f", 1000 / time), GameScreen.WIDTH - 80, GameScreen.HEIGHT - 20);
        t2 = System.currentTimeMillis();
        ThreadUtil.sleep(1000 / fps - (t2 - t1));
        time = time * (1.0 - weight) + (System.currentTimeMillis() - t1) * weight;
    }

}
