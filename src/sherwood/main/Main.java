package sherwood.main;

import sherwood.demo.game.layout.LayoutTestingLevel;
import sherwood.demo.game.states.transitions.FadeInLevel;
import sherwood.gameScreen.GameScreen;
import sherwood.screenStates.ScreenState;

import javax.swing.SwingUtilities;

public class Main {

    public static ScreenState DEFAULT_SCREENSTATE = new FadeInLevel(new LayoutTestingLevel(), 100);

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> GameScreen.start(DEFAULT_SCREENSTATE, "Kayin.pyoko"));
    }
}
