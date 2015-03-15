package sherwood.inputs.keyboard.control;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.continuous.ContinuousKeyboardInput;
import sherwood.inputs.keyboard.control.discrete.DiscreteKeyboardInput;

import java.awt.event.KeyEvent;
import java.util.*;

public class MixedControlKeyboardInput extends KeyboardInput {

    private KeyboardInput continuous;
    private KeyboardInput discrete;
    private EnumSet<Control> continuousKeys;

    public MixedControlKeyboardInput(EnumSet<Control> continuousKeys) {
        this.continuousKeys = continuousKeys;
        continuous = new ContinuousKeyboardInput();
        discrete = new DiscreteKeyboardInput();
    }

    @Override
    public void keyPressed (KeyEvent e) {
        Control control = ControlMap.getControl(e.getKeyCode());
        if (continuousKeys.contains(control))
            continuous.keyPressed(e);
        else
            discrete.keyPressed(e);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        Control control = ControlMap.getControl(e.getKeyCode());
        if (continuousKeys.contains(control))
            continuous.keyReleased(e);
        else
            discrete.keyReleased(e);
    }

    @Override
    public EnumSet<Control> keys () {
        EnumSet<Control> out = EnumSet.noneOf(Control.class);
        out.addAll(continuous.keys());
        out.addAll(discrete.keys());
        return out;
    }
}
