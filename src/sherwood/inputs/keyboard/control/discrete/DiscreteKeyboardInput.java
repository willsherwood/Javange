package sherwood.inputs.keyboard.control.discrete;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.ControlMap;

import java.awt.event.KeyEvent;
import java.util.EnumSet;

public class DiscreteKeyboardInput extends KeyboardInput {

    private EnumSet<Control> previous;
    private EnumSet<Control> current;

    public DiscreteKeyboardInput () {
        previous = EnumSet.noneOf(Control.class);
        current = EnumSet.noneOf(Control.class);
    }

    @Override
    public void keyPressed (KeyEvent e) {
        Control control = ControlMap.getControl(e.getKeyCode());
        if (!previous.contains(control)) {
            previous.add(control);
            current.add(control);
        }
    }

    @Override
    public void keyReleased (KeyEvent e) {
        Control control = ControlMap.getControl(e.getKeyCode());
        previous.remove(control);
        current.remove(control);
    }

    @Override
    public EnumSet<Control> keys () {
        EnumSet<Control> out = current.clone();
        current.clear();
        return out;
    }

}
