package sherwood.screenStates;

import sherwood.inputs.keyboard.control.Control;

import java.awt.*;
import java.util.*;

public class NullState extends ScreenState {

    private static final Font nullFont = new Font("monospaced", Font.PLAIN, 100);

    @Override
    public void draw (Graphics2D g) {
        Font t = g.getFont();
        g.setFont(nullFont);
        g.drawString("null", 100, 100);
        g.setFont(t);
    }

    @Override
    public void step (EnumSet<Control> keys) {

    }

    @Override
    public void init () {
        System.err.println("Null State instantiated");
        super.init();
    }
}
