package sherwood.gameScreen.inputs.keyboard.fullKeys;

import java.awt.event.KeyEvent;
import java.util.BitSet;

import sherwood.gameScreen.inputs.keyboard.KeyboardInput;

public class FullKeyboardInput extends KeyboardInput {

	protected BitSet bitset;

	public FullKeyboardInput() {
		bitset = new BitSet(256);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		bitset.set(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		bitset.set(e.getKeyCode(), false);
	}

	@Override
	public BitSet getBitSet() {
		return bitset;
	}

}
