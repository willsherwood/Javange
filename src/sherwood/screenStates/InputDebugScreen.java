package sherwood.screenStates;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.MixedControlKeyboardInput;

import java.awt.*;
import java.util.*;

public class InputDebugScreen extends ScreenState {

    protected EnumSet<Control> keys;

    public InputDebugScreen () {
        keys = EnumSet.noneOf(Control.class);
    }

    @Override
    public void init () {
        EnumSet<Control> continuous = EnumSet.of(Control.UP, Control.DOWN, Control.LEFT, Control.RIGHT);
        GameScreen.get().requestKeyInputMechanism(new MixedControlKeyboardInput(continuous));
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
    }

    @Override
    public void draw (Graphics2D g) {
        g.setColor(Color.RED);
        g.drawRect(0, 0, GameScreen.WIDTH - 1, GameScreen.HEIGHT - 1);
        for (int y = 0; y < Control.values().length; y++) {
            if (keys.contains(Control.values()[y]))
                g.fillRect(5, y * 20 + 8, 15, 15);
            g.drawString(Control.values()[y].toString(), 20, y * 20 + 20);
        }
    }

    @Override
    public void step (EnumSet<Control> keys) {
        this.keys = keys;
    }

}
