package sherwood.inputs.keyboard.fullKeys;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.ControlMap;

import java.awt.event.KeyEvent;
import java.util.*;

public class FullDiscreteKeyboardInput extends KeyboardInput {

    protected BitSet bitSets[];

    public FullDiscreteKeyboardInput () {
        bitSets = new BitSet[2];
        bitSets[0] = new BitSet(256);
        bitSets[1] = new BitSet(256);
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
    	if (i<0 || i>=256)
    		return;
        if (j) {
            if (!bitSets[0].get(i)) {
                bitSets[0].set(i, true);
                bitSets[1].set(i, true);
            }
        } else {
            bitSets[0].set(i, false);
            bitSets[1].set(i, false);
        }
    }


    @Override
    public BitSet getBitSet () {
    	BitSet out = (BitSet) bitSets[1].clone();
        bitSets[1].clear();
        return out;
    }

}
