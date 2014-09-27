package sherwood.demo.platform;

public class Player extends Entity {

	private boolean jump = true;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void step() {
		super.step();
	}

	@Override
	protected void doKinematics() {
		Block a = PlatformDemo.col.blockAt(x[1] + x[0], y[0]);
		if (a != null) {
			x[1] = 0;
			CollisionUtil.moveToContact(this, a, x[1] > 0 ? CollisionUtil.RIGHT
					: CollisionUtil.LEFT);
		}
		Block b = PlatformDemo.col.blockAt(x[0], y[0] + y[1]);
		if (b != null) {
			y[1] = 0;
			CollisionUtil.moveToContact(this, b, y[1] > 0 ? CollisionUtil.TOP
					: CollisionUtil.BOTTOM);
		}
		super.doKinematics();
	}

	public void jump() {
		super.ySet(Projectile.VELOCITY, -8.5f);
	}
}
