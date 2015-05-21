package sherwood.main;

import sherwood.demo.states.title.TitleScreen;
import sherwood.demo.states.transitions.FadeInState;
import sherwood.gameScreen.GameScreen;
import sherwood.screenStates.ScreenState;

import javax.swing.*;

public class Main {

    public static ScreenState DEFAULT_SCREENSTATE = new FadeInState(new TitleScreen(), 120);

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> GameScreen.start(DEFAULT_SCREENSTATE, "Javange"));
    }
}
