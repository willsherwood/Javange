package sherwood.screenStates;
import java.awt.Graphics2D;
import java.util.BitSet;


public abstract class ScreenState {

	public abstract void draw(Graphics2D g);
	
	public abstract void step(BitSet keys);
}
