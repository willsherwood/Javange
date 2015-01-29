package sherwood.screenStates;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.fullKeys.FullContinuousKeyboardInput;
import sherwood.inputs.keyboard.fullKeys.FullDiscreteKeyboardInput;

public class FullKeyInputDebugState extends ScreenState {

	private List<String> keysPressed;

	public FullKeyInputDebugState() {
		keysPressed = new ArrayList<>();
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < keysPressed.size(); i++)
			g.drawString(keysPressed.remove(0), 100, GameScreen.HEIGHT - 40 - i * 40);
	}

	@Override
	public void step(BitSet keys) {
		for (int i = keys.nextSetBit(0); i >= 0; i = keys.nextSetBit(i + 1))
			keysPressed.add(KeyboardInput.getKeyText(i));
	}

	@Override
	public void init() {
		GameScreen.get().requestKeyInputMechanism(new FullContinuousKeyboardInput());
		GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
	}

}
