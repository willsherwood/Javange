package sherwood.screenStates;

import java.awt.Graphics2D;
import java.util.BitSet;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.gameScreen.inputs.keyboard.control.ControlKeyboardInput;

public abstract class ScreenState {

	public abstract void draw(Graphics2D g);

	public abstract void step(BitSet keys);

	public void init() {
		GameScreen.get().requestKeyInputMechanism(new ControlKeyboardInput());
		GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
	}
}
