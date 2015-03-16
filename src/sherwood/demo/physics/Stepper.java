package sherwood.demo.physics;

import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public interface Stepper extends Entity {

    /**
     * an object that should trigger an effect when it collides with something else
     */
    void step (EnumSet<Control> keys);
}
