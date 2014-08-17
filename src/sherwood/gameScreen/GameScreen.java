package sherwood.gameScreen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

import sherwood.gameScreen.inputs.Input;
import sherwood.gameScreen.inputs.KeyboardInput;
import sherwood.screenStates.ScreenState;

public class GameScreen extends JFrame {

	public static final int TICKSPERSEC = 60;
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	protected ScreenState screenState;
	protected BufferedImage db;
	protected Graphics2D g;
	protected Input kbinput;

	protected JComponent drawComponent;

	public GameScreen(ScreenState screenState) {
		super();

		this.screenState = screenState;
		this.kbinput = new KeyboardInput();
		this.drawComponent = new DrawComponent();
		drawComponent.addKeyListener(kbinput);
		addKeyListener(kbinput);
		this.db = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.g = (Graphics2D) db.getGraphics();

		setSize(640, 640);
		setUndecorated(false);
		add(drawComponent);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					long t1 = System.currentTimeMillis();
					screenState.step(kbinput.getBitset());
					screenState.draw(g);
					paintToBuffer();
					sleep(1000 / TICKSPERSEC
							- (System.currentTimeMillis() - t1));
				}
			}

		}).start();

	}

	private void paintToBuffer() {
		Graphics2D gr = (Graphics2D) drawComponent.getGraphics();
		gr.drawImage(db, 0, 0, null);
	}

	protected void sleep(long time) {
		if (time <= 0)
			return;
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static final class DrawComponent extends JComponent {

		private static final long serialVersionUID = 213870082626004558L;

		public DrawComponent() {
			super();
			setFocusable(true);
		}
	}
}
