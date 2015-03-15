package sherwood.screenStates;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.continuous.ContinuousKeyboardInput;

import java.awt.*;
import java.util.*;

public abstract class ScreenState {

    public abstract void draw (Graphics2D g);

    public abstract void step (EnumSet<Control> keys);

    public void init () {
        GameScreen.get().requestKeyInputMechanism(new ContinuousKeyboardInput());
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
    }
}
