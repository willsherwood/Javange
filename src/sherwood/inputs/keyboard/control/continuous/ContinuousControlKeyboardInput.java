package sherwood.inputs.keyboard.control.continuous;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.ControlMap;

import java.awt.event.KeyEvent;
import java.util.*;

public class ContinuousControlKeyboardInput extends KeyboardInput {

    protected BitSet bitset;

    public ContinuousControlKeyboardInput () {
        bitset = new BitSet(Control.values().length);
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
        int k = Control.getCondensed(ControlMap.getControl(i));
        if (k < 0)
            return;
        bitset.set(k, j);
    }

    @Override
    public BitSet getBitSet () {
        return bitset;
    }

}
