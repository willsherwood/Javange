package sherwood.main;

import sherwood.demo.ControlSelectState;
import sherwood.gameScreen.GameScreen;
import sherwood.screenStates.ScreenState;

import javax.swing.*;

public class Main {

    public static ScreenState DEFAULT_SCREENSTATE = new ControlSelectState();

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> GameScreen.start(DEFAULT_SCREENSTATE, "Javange"));
    }
}
