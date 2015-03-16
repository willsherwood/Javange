package sherwood.inputs;

import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public interface Input {
    public abstract EnumSet<Control> keys ();
}
