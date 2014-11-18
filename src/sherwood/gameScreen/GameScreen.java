package sherwood.gameScreen;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

import sherwood.gameScreen.inputs.keyboard.KeyboardInput;
import sherwood.gameScreen.map.Mapping;
import sherwood.main.Main;
import sherwood.screenStates.ScreenState;

public class GameScreen extends JFrame {
	
	public int TICKSPERSEC = 50;
	public static int WIDTH = 800;
	public static int HEIGHT = 608;

	protected UpdateAlgorithm updateAlgorithm;
	protected ScreenState screenState;
	protected BufferedImage db;
	protected Graphics2D g;
	protected KeyboardInput kbinput;

	protected JComponent drawComponent;

	private static GameScreen game;
	
	public static GameScreen get() {
		if (game == null)
			game = new GameScreen(Main.DEFAULT_SCREENSTATE);
		return game;
	}
	
	private GameScreen(ScreenState screenState) {
		this.screenState = screenState;
		this.drawComponent = new DrawComponent();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(drawComponent);
		requestNewDimension(new Dimension(800, 608));
		setVisible(true);
		
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

	public KeyboardInput getKbinput() {
		return kbinput;
	}

	public void paintToBuffer(Mapping map) {
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
		this.kbinput = k;
		drawComponent.addKeyListener(this.kbinput);
		for (KeyListener kt : drawComponent.getKeyListeners()) {
			System.out.println(kt);
		}
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
