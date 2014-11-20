package sherwood.main;

import javax.swing.SwingUtilities;

import sherwood.demo.DemoState;
import sherwood.gameScreen.GameScreen;
import sherwood.iohandlers.ConfigHandler;
import sherwood.screenStates.RecordingState;
import sherwood.screenStates.ScreenState;

public class Main {

	public static ScreenState DEFAULT_SCREENSTATE = new RecordingState(new DemoState());

	public static void main(String[] args) {
		ConfigHandler.init();
		SwingUtilities.invokeLater(() -> GameScreen.get());
	}
}
