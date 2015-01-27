package sherwood.screenStates;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.continuous.ContinuousControlKeyboardInput;

import java.awt.*;
import java.util.*;

public abstract class ScreenState {

    public abstract void draw (Graphics2D g);

    public abstract void step (BitSet keys);

    public void init () {
        GameScreen.get().requestKeyInputMechanism(new ContinuousControlKeyboardInput());
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
    }
}
