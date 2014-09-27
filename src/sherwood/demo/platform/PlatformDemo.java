package sherwood.demo.platform;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import sherwood.gameScreen.GameScreen;
import sherwood.gameScreen.inputs.Control;
import sherwood.screenStates.ScreenState;

public class PlatformDemo extends ScreenState {

	public static CollisionUtil col;
	private Player player;
	private List<Block> blocks;

	public PlatformDemo() {
		player = new Player(300, 400, 32, 32);
		player.ySet(Projectile.ACCELERATION, 0.4f);

		blocks = new ArrayList<>();
		for (int i = 0; i < GameScreen.WIDTH / 32; i++)
			blocks.add(new Block(i * 32, GameScreen.HEIGHT - 32));
		for (int i = 0; i < GameScreen.HEIGHT / 32; i++)
			blocks.add(new Block(0, i * 32));
		for (int i = 0; i < GameScreen.WIDTH / 32; i++)
			blocks.add(new Block(i * 32, 0));
		for (int i = 0; i < GameScreen.HEIGHT / 32; i++)
			blocks.add(new Block(GameScreen.WIDTH - 32, i * 32));
		blocks.add(new Block(311, 311));
		col = new CollisionUtil(blocks);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.pink);
		g.fillRect((int) player.xGet(Projectile.POSITION),
				(int) player.yGet(Projectile.POSITION), player.width,
				player.height);
		for (Block block : blocks)
			g.drawRect((int) block.xGet(Projectile.POSITION),
					(int) block.yGet(Projectile.POSITION), 32, 32);
	}

	@Override
	public void step(BitSet keys) {
		player.step(8, -9);
		boolean right = keys.get(Control.getCondensed(Control.RIGHT));
		boolean left = keys.get(Control.getCondensed(Control.LEFT));
		if (right)
			player.xSet(Projectile.VELOCITY, 3f);
		if (left)
			player.xSet(Projectile.VELOCITY, -3f);
		if (!right && !left)
			player.xSet(Projectile.VELOCITY, 0);
		if (keys.get(Control.getCondensed(Control.JUMP)))
			player.jump();
	}

}
