package sherwood.demo;

import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.gameScreen.inputs.keyboard.fullKeys.FullKeyboardInput;
import sherwood.screenStates.ScreenState;
import sherwood.screenStates.objective.Drawable;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DemoState extends ScreenState {

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
