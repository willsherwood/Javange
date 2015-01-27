package sherwood.inputs.keyboard.control.discrete;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.ControlMap;

import java.awt.event.KeyEvent;
import java.util.*;

public class DiscreteControlKeyboardInput extends KeyboardInput {

    protected BitSet[] bitSets;

    public DiscreteControlKeyboardInput () {
        bitSets = new BitSet[2];
        bitSets[0] = new BitSet(Control.values().length); // previous
        bitSets[1] = new BitSet(Control.values().length); // current
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
        if (j) {
            if (!bitSets[0].get(k)) {
                bitSets[0].set(k, true);
                bitSets[1].set(k, true);
            }
        } else {
            bitSets[0].set(k, false);
            bitSets[1].set(k, false);
        }
    }

    @Override
    public BitSet getBitSet () {
        if (!bitSets[1].isEmpty()) {
            System.out.println(bitSets[1].nextSetBit(0));
        }
        BitSet out = (BitSet) bitSets[1].clone();
        bitSets[1].clear();
        return out;
    }

}
