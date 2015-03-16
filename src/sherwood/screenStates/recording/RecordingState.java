package sherwood.screenStates.recording;

import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class RecordingState extends ScreenState {

    protected ScreenState screen;
    protected List<EnumSet<Control>> recordedKeys;

    public RecordingState (ScreenState screen) {
        this.screen = screen;
        recordedKeys = new ArrayList<>();
    }

    @Override
    public void init () {
        screen.init();
    }

    public List<EnumSet<Control>> getRecordedKeys () {
        return recordedKeys;
    }

    @Override
    public void draw (Graphics2D g) {
        g.drawString("size: " + recordedKeys.size(), 100, 100);
        screen.draw(g);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        recordedKeys.add(keys.clone());
        screen.step(recordedKeys.get(recordedKeys.size() - 1));
    }
}
