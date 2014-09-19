package sherwood.gameScreen;

import java.awt.Graphics2D;

import sherwood.gameScreen.inputs.KeyboardInput;
import sherwood.screenStates.ScreenState;

/**
 * 
 */
public interface UpdateAlgorithm {

	/**
	 * @param screenState
	 * @param graphics
	 * @param gameScreen
	 * @param input
	 */
	public void update(ScreenState screenState, Graphics2D graphics,
			GameScreen gameScreen, KeyboardInput input);
}
