package sherwood.gameScreen;

import java.awt.Graphics2D;

import sherwood.gameScreen.inputs.KeyboardInput;
import sherwood.screenStates.ScreenState;

public class FPSUpdateAlgorithm implements UpdateAlgorithm {

	@Override
	public void update(ScreenState screenState, Graphics2D graphics,
			GameScreen gameScreen, KeyboardInput input) {
		long t1 = System.currentTimeMillis();
		screenState.step(input.getBitset());
		screenState.draw(graphics);
		gameScreen.paintToBuffer();
		ThreadUtil.sleep(1000 / GameScreen.get().TICKSPERSEC
				- (System.currentTimeMillis() - t1));
	}

}
