package sherwood.gameScreen.inputs.mouse;

import java.awt.Point;
import java.util.BitSet;

public interface MouseDecoder {

	public Point decode(BitSet b);
}
