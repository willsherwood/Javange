package sherwood.gameScreen;

import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;

public interface UpdateAlgorithm {

    public void update (ScreenState screenState, Graphics2D graphics,
                        GameScreen gameScreen, KeyboardInput input);
}
