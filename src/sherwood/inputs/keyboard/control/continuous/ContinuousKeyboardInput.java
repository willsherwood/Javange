package sherwood.inputs.keyboard.control.continuous;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.ControlMap;

import java.awt.event.KeyEvent;
import java.util.EnumSet;

public class ContinuousKeyboardInput extends KeyboardInput {

    protected EnumSet<Control> keys;

    public ContinuousKeyboardInput () {
        keys = EnumSet.noneOf(Control.class);
    }

    @Override
    public void keyPressed (KeyEvent e) {
        Control c = ControlMap.getControl(e.getKeyCode());
        if (c != null)
            set(c, true);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        Control c = ControlMap.getControl(e.getKeyCode());
        if (c != null)
            set(c, false);
    }

    public void set (Control i, boolean j) {
        if (j)
            keys.add(i);
        else
            keys.remove(i);
    }

    @Override
    public EnumSet<Control> keys () {
        return keys;
    }

}
