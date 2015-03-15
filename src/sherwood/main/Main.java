package sherwood.main;

import sherwood.gameScreen.GameScreen;
import sherwood.screenStates.InputDebugScreen;
import sherwood.screenStates.ScreenState;

import javax.swing.*;

public class Main {

    public static ScreenState DEFAULT_SCREENSTATE = new InputDebugScreen();

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> GameScreen.start(DEFAULT_SCREENSTATE, "Javange"));
    }
}
