package sherwood.inputs.keyboard.control.continuous;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.ControlMap;

import java.awt.event.KeyEvent;
import java.util.*;

public class ContinuousKeyboardInput extends KeyboardInput {

    protected EnumSet<Control> keys;

    public ContinuousKeyboardInput () {
        keys = EnumSet.noneOf(Control.class);
    }

    @Override
    public void keyPressed (KeyEvent e) {
        set(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        set(e.getKeyCode(), false);
    }

    public void set (int i, boolean j) {
        if (j)
            keys.add(ControlMap.getControl(i));
        else
            keys.remove(ControlMap.getControl(i));
    }

    @Override
    public EnumSet<Control> keys () {
        return keys;
    }

}
