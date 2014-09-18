package sherwood.gameScreen;

import java.awt.Graphics2D;

import sherwood.gameScreen.inputs.Input;
import sherwood.screenStates.ScreenState;

public class FPSUpdateAlgorithm implements UpdateAlgorithm {

	@Override
	public void update(ScreenState screenState, Graphics2D graphics,
			GameScreen gameScreen, Input input) {
		long t1 = System.currentTimeMillis();
		screenState.step(input.getBitset());
		screenState.draw(graphics);
		gameScreen.paintToBuffer();
		ThreadUtil.sleep(1000 / GameScreen.TICKSPERSEC
				- (System.currentTimeMillis() - t1));
	}

}
