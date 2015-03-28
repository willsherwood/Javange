package sherwood.main;

import sherwood.demo.states.levels.StartingLevel;
import sherwood.gameScreen.GameScreen;
import sherwood.screenStates.ScreenState;

import javax.swing.SwingUtilities;

public class Main {

    public static ScreenState DEFAULT_SCREENSTATE = new StartingLevel();

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> GameScreen.start(DEFAULT_SCREENSTATE, "Javange"));
    }
}
