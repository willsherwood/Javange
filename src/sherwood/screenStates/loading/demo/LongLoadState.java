package sherwood.screenStates.loading.demo;

import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.util.*;

public class LongLoadState extends ScreenState {

    @Override
    public void init () {
        System.out.println("start called");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw (Graphics2D g) {
        g.drawString("hello, world", 100, 100);
    }

    @Override
    public void step (EnumSet<Control> keys) {

    }
}
