package sherwood.demo.platform;

import java.util.List;

public class CollisionUtil {

	public static final int BOTTOM = 0, TOP = 1, LEFT = 2, RIGHT = 3;
	private List<Block> blocks;

	public CollisionUtil(List<Block> blocks) {
		this.blocks = blocks;
	}

	/**
	 * the *SIDE* of A is moved into contact with opposite side of B
	 */
	public static void moveToContact(Entity a, Entity b, int side) {
		switch (side) {
		case LEFT:
			a.xSet(Projectile.POSITION, b.xGet(Projectile.POSITION) + b.width);
			break;
		case RIGHT:
			a.xSet(Projectile.POSITION, b.xGet(Projectile.POSITION) - a.width);
			break;
		case TOP:
			a.ySet(Projectile.POSITION, b.yGet(Projectile.POSITION) + b.height);
			break;
		case BOTTOM:
			a.ySet(Projectile.POSITION, b.yGet(Projectile.POSITION) - a.height);
			break;
		default:
			throw new IllegalArgumentException("side must be between 0 and 3");
		}
	}

	public Block blockAt(float x, float y) {
		for (Block b : blocks) {
			float bx = b.xGet(Projectile.POSITION);
			float by = b.yGet(Projectile.POSITION);
			if (x >= bx && x < bx + 32)
				if (y >= by && y < by + 32)
					return b;
		}
		return null;
	}
}
