package sherwood.screenStates.loading;

import sherwood.gameScreen.GameScreen;
import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.util.*;

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
    public void step (BitSet keys) {
    }

    @Override
    public void init () {
    }
}
