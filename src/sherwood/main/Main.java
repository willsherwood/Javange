package sherwood.main;

import sherwood.demo.DemoState;
import sherwood.gameScreen.GameScreen;
import sherwood.iohandlers.ConfigHandler;
import sherwood.screenStates.ScreenState;
import sherwood.screenStates.recording.RecordingWrapperState;

import javax.swing.*;

public class Main {

    public static ScreenState DEFAULT_SCREENSTATE = new RecordingWrapperState(DemoState.class);

    public static void main (String[] args) {
        ConfigHandler.init();
        SwingUtilities.invokeLater(GameScreen::get);
    }
}
