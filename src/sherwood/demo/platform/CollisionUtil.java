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
		System.out.println(b.xGet(Projectile.POSITION));
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
			float dx = b.xGet(Projectile.POSITION) - x;
			float dy = b.yGet(Projectile.POSITION) - y;
			if (dx < 32 && dx >= 0 && dy < 32 & dy >= 0)
				return b;
		}
		return null;
	}
}
