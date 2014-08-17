package sherwood.gameScreen.inputs;
import java.awt.event.KeyAdapter;
import java.util.BitSet;

public abstract class Input extends KeyAdapter {

	public abstract BitSet getBitset();
}
