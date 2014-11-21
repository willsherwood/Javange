package sherwood.gameScreen;

import sherwood.gameScreen.inputs.keyboard.KeyboardInput;
import sherwood.gameScreen.map.Mapping;
import sherwood.main.Main;
import sherwood.screenStates.ScreenState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameScreen extends JFrame {

	public static int WIDTH = 800;
	public static int HEIGHT = 608;
	private static GameScreen game;
	public final int DEFAULT_TICKSPERSEC = 60;
	protected UpdateAlgorithm updateAlgorithm;
	protected ScreenState screenState;
	protected BufferedImage db;
	protected Graphics2D g;
	protected KeyboardInput keyboardInput;
	protected JComponent drawComponent;

	private GameScreen (ScreenState screenState) {
		this.screenState = screenState;
		this.drawComponent = new DrawComponent();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(drawComponent);
		requestNewDimension(new Dimension(800, 608));
		setVisible(true);

		Thread s = new UpdateThread(this::getScreenState, this::getGraphics2D, this::getKeyboardInput, GameScreen::get, this::getUpdateAlgorithm);
		s.start();
	}
	
	public static GameScreen get() {
		if (game == null)
			game = new GameScreen(Main.DEFAULT_SCREENSTATE);
		return game;
	}

	public ScreenState getScreenState() {
		return screenState;
	}
	
	public UpdateAlgorithm getUpdateAlgorithm() {
		return updateAlgorithm;
	}

	public Graphics2D getGraphics2D () {
		return g;
	}

	public KeyboardInput getKeyboardInput () {
		return keyboardInput;
	}

	public void paintToBuffer(Mapping map) {
		getGraphics2D().setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Graphics2D gr = (Graphics2D) drawComponent.getGraphics();
		gr.drawImage(map == null? db : map.map(db), 0, 0, null);
		g.clearRect(0, 0, WIDTH, HEIGHT);
	}
	
	public void requestScreenState(ScreenState s) {
		this.screenState = s;
		s.init();
	}
	
	public void requestUpdateAlgorithm(UpdateAlgorithm u) {
		this.updateAlgorithm = u;
	}
	
	public void requestKeyInputMechanism(KeyboardInput k) {
		drawComponent.removeKeyListener(this.keyboardInput);
		this.keyboardInput = k;
		drawComponent.addKeyListener(this.keyboardInput);
	}
	
	public void requestNewDimension(Dimension d) {
		WIDTH = d.width;
		HEIGHT = d.height;
		this.db = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.g = (Graphics2D) db.getGraphics();
		this.pack();
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
