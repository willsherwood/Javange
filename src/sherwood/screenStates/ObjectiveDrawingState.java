package sherwood.screenStates;

import java.awt.Graphics2D;
import java.util.BitSet;

public abstract class ObjectiveDrawingState extends ScreenState {

	private Drawable[] toDraw;
	
	protected void draw(Drawable... d) {
		toDraw = d;
	}
	
	/**
	 * Should not be called from subclass. Act as private
	 */
	public void draw(Graphics2D g) {
		for (Drawable d : toDraw)
			d.draw(g);
	}

	public abstract void step(BitSet keys);
}
