package sherwood.screenStates;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import sherwood.gameScreen.GameScreen;

public class RecordingState extends ScreenState {

	protected ScreenState screen;
	protected List<BitSet> recordedKeys;

	public RecordingState(ScreenState screen) {
		this.screen = screen;
		recordedKeys = new ArrayList<>();
	}

	@Override
	public void init() {
		screen.init();
	}

	public List<BitSet> getRecordedKeys() {
		return recordedKeys;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawString("bitset size: " + recordedKeys.size(), 100, 100);
		screen.draw(g);
	}

	@Override
	public void step(BitSet keys) {
		recordedKeys.add((BitSet)keys.clone());
		screen.step(recordedKeys.get(recordedKeys.size() - 1));
		if (recordedKeys.size() > 150) {
			try {
				GameScreen.get().requestScreenState(new PlaybackState(screen.getClass().newInstance(), recordedKeys));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
