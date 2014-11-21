package sherwood.gameScreen.inputs.mouse;

import java.awt.*;
import java.util.*;

public class IntegerPointMouseInput extends MouseInput {

    // first 16 bits, mouseX
    // next 16 bits, mouseY
    // last bit: mouseDown
    private BitSet bitSet;

    public IntegerPointMouseInput () {
        super();
        bitSet = new BitSet(33);
    }

    @Override
    public BitSet getBitSet () {
        return bitSet;
    }

    @Override
    public boolean isMouseDown () {
        return bitSet.get(32);
    }

    @Override
    public Point decode (BitSet b) {
        byte[] out = b.toByteArray();
        return new Point((out[0] << 24) + (out[1] << 16) + (out[2] << 8)
                + out[3], (out[4] << 24) + (out[5] << 16) + (out[6] << 8)
                + out[7]);
    }

}
