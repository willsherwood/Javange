package sherwood.main;

import javax.swing.SwingUtilities;

import sherwood.gameScreen.GameScreen;
import sherwood.iohandlers.ConfigHandler;
import sherwood.screenStates.InputDebugScreen;

public class Main {

	public static void main(String[] args) {
		ConfigHandler.init();
		SwingUtilities.invokeLater(() -> new GameScreen(
				new InputDebugScreen()));
	}
}
