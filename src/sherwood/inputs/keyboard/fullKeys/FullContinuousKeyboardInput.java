package sherwood.inputs.keyboard.fullKeys;

import sherwood.inputs.keyboard.KeyboardInput;

import java.awt.event.KeyEvent;
import java.util.*;

public class FullContinuousKeyboardInput extends KeyboardInput {

    protected BitSet bitset;

    public FullContinuousKeyboardInput () {
        bitset = new BitSet(256);
    }

    @Override
    public void keyPressed (KeyEvent e) {
        bitset.set(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        bitset.set(e.getKeyCode(), false);
    }

    @Override
    public BitSet getBitSet () {
        return bitset;
    }

}
