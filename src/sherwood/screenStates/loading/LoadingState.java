package sherwood.screenStates.loading;

import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class LoadingState extends ScreenState {

    private LoadingState () {

    }

    public LoadingState (ScreenState p) {
        new Thread(() -> {
            p.init();
            GameScreen.get().requestScreenState(p);
        }).start();
    }

    @Override
    public void draw (Graphics2D g) {
        g.drawString("LOADING . . .", GameScreen.WIDTH / 2, GameScreen.HEIGHT / 2);
    }

    @Override
    public void step (EnumSet<Control> keys) {
    }

    @Override
    public void init () {
    }
}
