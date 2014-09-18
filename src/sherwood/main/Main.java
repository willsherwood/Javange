package sherwood.main;

import javax.swing.SwingUtilities;

import sherwood.gameScreen.GameScreen;
import sherwood.iohandlers.ConfigHandler;
import sherwood.screenStates.InputDebugScreen;
import sherwood.screenStates.ScreenState;

public class Main {

	public static ScreenState DEFAULT_SCREENSTATE = new InputDebugScreen();

	public static void main(String[] args) {
		ConfigHandler.init();
		SwingUtilities.invokeLater(() -> GameScreen.get());
	}
}
