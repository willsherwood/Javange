package sherwood.gameScreen.inputs.keyboard.fullKeys;

import sherwood.gameScreen.inputs.keyboard.KeyboardInput;

import java.awt.event.KeyEvent;
import java.util.*;

public class FullKeyboardInput extends KeyboardInput {

    protected BitSet bitset;

    public FullKeyboardInput () {
        bitset = new BitSet(256);
    }

    public static String getKey (int i) {
        return KeyEvent.getKeyText(i);
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
