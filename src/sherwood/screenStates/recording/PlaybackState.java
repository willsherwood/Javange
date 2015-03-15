package sherwood.screenStates.recording;

import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PlaybackState extends ScreenState {

    protected ScreenState screen;
    protected List<EnumSet<Control>> keys;
    protected int index;

    public PlaybackState (ScreenState screen, List<EnumSet<Control>> keys) {
        this.screen = screen;
        this.keys = keys;
    }

    @Override
    public void init () {
        screen.init();
    }

    @Override
    public void draw (Graphics2D g) {
        g.drawString("playback state index: " + index, 10, 10);
        screen.draw(g);
    }

    @Override
    public void step (EnumSet<Control> keys) {
        if (index < this.keys.size())
            screen.step(this.keys.get(index++));
    }

}
