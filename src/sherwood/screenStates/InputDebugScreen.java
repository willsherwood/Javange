package sherwood.screenStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.BitSet;

import sherwood.gameScreen.GameScreen;
import sherwood.gameScreen.inputs.Control;

public class InputDebugScreen extends ScreenState {

	protected BitSet keys;
	protected int iteration;
	private float[] x = {100f, 0f, 0f};
	private float[] y = {100f, 0f, 0.098f};

	public InputDebugScreen() {
		keys = new BitSet(0);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawRect((int)x[0], (int)y[0], 5, 5);
		g.drawRect(0, 0, GameScreen.WIDTH-1, GameScreen.HEIGHT-1);
		for (int y = 0; y < Control.values().length; y++) {
			if (keys.get(y))
				g.fillRect(5, y * 20 + 8, 15, 15);
			g.drawString(Control.values()[y].toString(), 20, y * 20 + 20);
		}
		System.out.println(Arrays.toString(x));
	}

	@Override
	public void step(BitSet keys) {
		
		x[0] += x[1] += x[2];
		y[0] += y[1] += y[2];
		
		this.keys = keys;
		if (keys.get(Control.getCondensed(Control.UP)) || y[0] > GameScreen.HEIGHT)
			y[1] = -2;
		if (keys.get(Control.getCondensed(Control.DOWN)))
			y[1] += 0.098;
		if (keys.get(Control.getCondensed(Control.LEFT)))
			x[2] -= 0.002f;
		else
			x[2] += (x[2] < 0) ? 0.002f : 0;
		if (keys.get(Control.getCondensed(Control.RIGHT)))
			x[2] += 0.002f;
		else
			x[2] += (x[2] > 0) ? -0.002f : 0;
	}

}
