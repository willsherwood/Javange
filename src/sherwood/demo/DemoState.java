package sherwood.demo;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.gameScreen.inputs.keyboard.fullKeys.FullKeyboardInput;
import sherwood.screenStates.ScreenState;
import sherwood.screenStates.objective.Drawable;

public class DemoState extends ScreenState {

	private BitSet currentKeys;
	private List<CharacterParticle> particles;

	@Override
	public void init() {
		GameScreen.get().requestKeyInputMechanism(new FullKeyboardInput());
		GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm());
		particles = new ArrayList<>();
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < particles.size(); i++) {
			Drawable d = particles.get(i);
			if (!d.draw(g)) {
				particles.remove(d);
				i--;
			}
		}

	}

	@Override
	public void step(BitSet keys) {
		for (int i = keys.nextSetBit(0); i >= 0; i = keys.nextSetBit(i + 1)) {
			particles.add(new CharacterParticle(FullKeyboardInput.getKey(i), (i/16) * 32 + 16, (i%16) * 32 + 16));
		}
	}

}
