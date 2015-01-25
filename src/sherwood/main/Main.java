package sherwood.main;

import sherwood.demo.ControlSelectState;
import sherwood.gameScreen.GameScreen;
import sherwood.iohandlers.ConfigHandler;
import sherwood.screenStates.ScreenState;

import javax.swing.*;

public class Main {

    public static ScreenState DEFAULT_SCREENSTATE = new ControlSelectState();

    public static void main (String[] args) {
        ConfigHandler.init();
        SwingUtilities.invokeLater(GameScreen::get);
    }
}
