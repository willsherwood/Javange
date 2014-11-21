package sherwood.gameScreen.inputs.mouse;

import sherwood.gameScreen.inputs.Input;

import java.awt.event.MouseAdapter;

public abstract class MouseInput extends MouseAdapter implements Input, MouseDecoder {

    public abstract boolean isMouseDown ();
}
