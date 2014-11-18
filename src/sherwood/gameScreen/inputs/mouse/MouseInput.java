package sherwood.gameScreen.inputs.mouse;

import java.awt.event.MouseAdapter;

import sherwood.gameScreen.inputs.Input;

public abstract class MouseInput extends MouseAdapter implements Input, MouseDecoder {

	public abstract boolean isMouseDown();
}
