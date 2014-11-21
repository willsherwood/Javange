package sherwood.screenStates;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.gameScreen.inputs.keyboard.control.ControlKeyboardInput;

import java.awt.*;
import java.util.*;

public abstract class ScreenState {

    public abstract void draw (Graphics2D g);

    public abstract void step (BitSet keys);

    public void init () {
        GameScreen.get().requestKeyInputMechanism(new ControlKeyboardInput());
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
    }
}
