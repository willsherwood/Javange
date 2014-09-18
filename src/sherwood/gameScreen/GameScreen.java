package sherwood.gameScreen;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

import sherwood.gameScreen.inputs.Input;
import sherwood.gameScreen.inputs.KeyboardInput;
import sherwood.main.Main;
import sherwood.screenStates.ScreenState;

public class GameScreen extends JFrame {
	
	public static int TICKSPERSEC = 60;
	public static int WIDTH = 640;
	public static int HEIGHT = 480;

	protected UpdateAlgorithm updateAlgorithm;
	protected ScreenState screenState;
	protected BufferedImage db;
	protected Graphics2D g;
	protected Input kbinput;

	protected JComponent drawComponent;

	private static GameScreen game;
	
	public static GameScreen get() {
		if (game == null)
			game = new GameScreen(Main.DEFAULT_SCREENSTATE);
		return game;
	}
	
	private GameScreen(ScreenState screenState) {
		this.updateAlgorithm = new FPSUpdateAlgorithm();
		this.screenState = screenState;
		this.kbinput = new KeyboardInput();
		this.drawComponent = new DrawComponent();
		drawComponent.addKeyListener(kbinput);
		this.db = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.g = (Graphics2D) db.getGraphics();

		setUndecorated(false);
		add(drawComponent);
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Thread s = new UpdateThread(this::getScreenState, this::getG, this::getKbinput, GameScreen::get, this::getUpdateAlgorithm);
		s.start();
	}

	public ScreenState getScreenState() {
		return screenState;
	}
	
	public UpdateAlgorithm getUpdateAlgorithm() {
		return updateAlgorithm;
	}

	public Graphics2D getG() {
		return g;
	}

	public Input getKbinput() {
		return kbinput;
	}

	public void paintToBuffer() {
		Graphics2D gr = (Graphics2D) drawComponent.getGraphics();
		gr.drawImage(db, 0, 0, null);
		g.clearRect(0, 0, 640, 640);
	}

	public static final class DrawComponent extends JComponent {

		private static final long serialVersionUID = 213870082626004558L;

		public DrawComponent() {
			super();
			setFocusable(true);
		}
		
		@Override public Dimension getPreferredSize() {
			return new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT);
		}
	}
}
