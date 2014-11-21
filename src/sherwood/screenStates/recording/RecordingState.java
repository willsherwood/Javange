package sherwood.screenStates.recording;

import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RecordingState extends ScreenState {

    protected ScreenState screen;
    protected List<BitSet> recordedKeys;

    public RecordingState (ScreenState screen) {
        this.screen = screen;
        recordedKeys = new ArrayList<>();
    }

    @Override
    public void init () {
        screen.init();
    }

    public List<BitSet> getRecordedKeys () {
        return recordedKeys;
    }

    @Override
    public void draw (Graphics2D g) {
        g.drawString("bitset size: " + recordedKeys.size(), 100, 100);
        screen.draw(g);
    }

    @Override
    public void step (BitSet keys) {
        recordedKeys.add((BitSet) keys.clone());
        screen.step(recordedKeys.get(recordedKeys.size() - 1));
    }
}
