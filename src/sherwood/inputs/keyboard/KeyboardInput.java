package sherwood.inputs.keyboard;

import sherwood.inputs.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public abstract class KeyboardInput extends KeyAdapter implements Input {

    public static String getKeyText (int i) {
        return KeyEvent.getKeyText(i);
    }
}
