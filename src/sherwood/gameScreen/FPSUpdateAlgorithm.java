package sherwood.gameScreen;

import sherwood.gameScreen.inputs.keyboard.KeyboardInput;
import sherwood.gameScreen.map.Mapping;
import sherwood.screenStates.ScreenState;

import java.awt.*;

public class FPSUpdateAlgorithm implements UpdateAlgorithm {

	private Mapping map;
	private double time;
	private double weight = 0.1;
	private long t1, t2;
	
	public FPSUpdateAlgorithm(Mapping map) {
		this.map = map;
	}
	
	public FPSUpdateAlgorithm() {
		this(null);
	}
	
	public void requestNewMapping(Mapping map) {
		this.map = map;
	}
	
	@Override
	public void update(ScreenState screenState, Graphics2D graphics,
			GameScreen gameScreen, KeyboardInput input) {
		t1 = System.currentTimeMillis();
		screenState.step(input.getBitSet());
		screenState.draw(graphics);
		gameScreen.paintToBuffer(map);
		graphics.setColor(Color.WHITE);
		graphics.drawString(String.format("FPS: %.1f", 1000 / time), GameScreen.WIDTH - 80, GameScreen.HEIGHT - 20);
		t2 = System.currentTimeMillis();
		ThreadUtil.sleep(1000 / GameScreen.get().DEFAULT_TICKSPERSEC - (t2 - t1));
		time = time * (1.0 - weight) + (System.currentTimeMillis() - t1) * weight;
	}

}
