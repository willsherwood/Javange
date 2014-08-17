package sherwood.screenStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.BitSet;

import sherwood.gameScreen.inputs.Control;

public class InputDebugScreen extends ScreenState {

	protected BitSet keys;
	protected int iteration;

	public InputDebugScreen() {
		keys = new BitSet(0);
	}

	@Override
	public void draw(Graphics2D g) {
		g.clearRect(0, 0, 640, 640);
		g.setColor(Color.RED);
		for (int y = 0; y < Control.values().length; y++) {
			if (keys.get(y))
				g.fillRect(5, y * 20 + 8, 15, 15);
			g.drawString(Control.values()[y].toString(), 20, y * 20 + 20);
		}
	}

	@Override
	public void step(BitSet keys) {
		this.keys = keys;
	}

}
