package sherwood.gameScreen;

import java.awt.Color;
import java.awt.Graphics2D;

import sherwood.gameScreen.inputs.KeyboardInput;
import sherwood.gameScreen.map.Mapping;
import sherwood.screenStates.ScreenState;

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
		screenState.step(input.getBitset());
		screenState.draw(graphics);
		gameScreen.paintToBuffer(map);
		graphics.setColor(Color.WHITE);
		graphics.drawString(String.format("FPS: %.1f", 1000 / time), GameScreen.WIDTH - 80, GameScreen.HEIGHT - 20);
		t2 = System.currentTimeMillis();
		ThreadUtil.sleep(1000 / GameScreen.get().TICKSPERSEC - (t2 - t1));
		time = time * (1.0 - weight) + (System.currentTimeMillis() - t1) * weight;
		// no FPS cap
		
	}

}