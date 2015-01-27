package sherwood.inputs.keyboard.control;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.continuous.ContinuousControlKeyboardInput;
import sherwood.inputs.keyboard.control.discrete.DiscreteControlKeyboardInput;

import java.awt.event.KeyEvent;
import java.util.*;

public class MixedControlKeyboardInput extends KeyboardInput {

    protected BitSet keys;
    protected KeyboardInput continuous;
    protected KeyboardInput discrete;

    /**
     * constructs a keyboard input with the availability of both continuous and discrete
     * keys. A true value in the control bit set means the key is continuous.
     */
    public MixedControlKeyboardInput (BitSet keys) {
        this.keys = keys;
        this.continuous = new ContinuousControlKeyboardInput();
        this.discrete = new DiscreteControlKeyboardInput();
    }

    @Override
    public void keyPressed (KeyEvent e) {
        int k = Control.getCondensed(ControlMap.getControl(e.getKeyCode()));
        if (k < 0)
            return;
        if (keys.get(k))
            continuous.keyPressed(e);
        else
            discrete.keyPressed(e);
    }

    @Override
    public void keyReleased (KeyEvent e) {
        continuous.keyReleased(e);
        discrete.keyReleased(e);
    }

    @Override
    public BitSet getBitSet () {
        BitSet out = new BitSet(Control.values().length);
        BitSet disc = discrete.getBitSet();
        BitSet cont = continuous.getBitSet();
        for (int i = 0; i < Control.values().length; i++) {
            if (keys.get(i))
                out.set(i, cont.get(i));
            else
                out.set(i, disc.get(i));
        }
        return out;
    }
}
