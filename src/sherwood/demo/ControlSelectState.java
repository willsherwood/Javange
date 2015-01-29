package sherwood.demo;

import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.fullKeys.FullDiscreteKeyboardInput;
import sherwood.iohandlers.ConfigHandler;
import sherwood.screenStates.InputDebugScreen;
import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.util.*;

public class ControlSelectState extends ScreenState {

    private int index;
    private int sleep;

    @Override
    public void draw (Graphics2D g) {
        g.drawString(Control.values()[index].toString(), 100, 100);
    }

    @Override
    public void step (BitSet keys) {
        if (sleep != 0) {
            sleep--;
            return;
        }
        if (!keys.isEmpty()) {
            ConfigHandler.save(Control.values()[index].toString(), keys.nextSetBit(0) + "");
            sleep = 10;
            index++;
            if (index == Control.values().length) {
                index = 0;
                GameScreen.get().requestScreenStateAndInit(new InputDebugScreen());
            }
        }
    }

    @Override
    public void init () {
        super.init();
        GameScreen.get().requestKeyInputMechanism(new FullDiscreteKeyboardInput());
    }
}
