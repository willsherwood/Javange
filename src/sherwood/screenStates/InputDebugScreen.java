package sherwood.screenStates;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.BitSet;

import sherwood.gameScreen.GameScreen;
import sherwood.gameScreen.inputs.Control;

public class InputDebugScreen extends ScreenState {

	protected BitSet keys;
	protected int iteration;

	public InputDebugScreen() {
		keys = new BitSet(0);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect(0, 0, GameScreen.WIDTH - 1, GameScreen.HEIGHT - 1);
		for (int y = 0; y < Control.values().length; y++) {
			if (keys.get(y))
				g.fillRect(5, y * 20 + 8, 15, 15);
			g.drawString(Control.values()[y].toString(), 20, y * 20 + 20);
		}
	}

	@Override
	public void step(BitSet keys) {
		this.keys = keys;
		if (keys.get(Control.getCondensed(Control.ACTION))) {
			GameScreen.get().requestNewDimension(new Dimension(GameScreen.WIDTH + 10, GameScreen.HEIGHT + 10));
		}
	}

}
