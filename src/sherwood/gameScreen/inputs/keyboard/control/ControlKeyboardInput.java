package sherwood.gameScreen.inputs.keyboard.control;

import sherwood.gameScreen.inputs.keyboard.KeyboardInput;

import java.awt.event.KeyEvent;
import java.util.*;

public class ControlKeyboardInput extends KeyboardInput {

    protected BitSet bitset;

    public ControlKeyboardInput () {
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
