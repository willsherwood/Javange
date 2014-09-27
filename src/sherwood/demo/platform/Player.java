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
		Block a1 = PlatformDemo.col.blockAt(x[1] + x[0], y[0]);
		Block a2 = PlatformDemo.col.blockAt(x[1] + x[0] + width, y[0]);
		Block b1 = PlatformDemo.col.blockAt(x[0], y[0] + y[1]);
		Block b2 = PlatformDemo.col.blockAt(x[0], y[0] + y[1] + height);
		
		if (a1 != null) {
			CollisionUtil.moveToContact(this, a1, CollisionUtil.LEFT);
			x[1] = 0;
		}
		if (a2 != null) {
			CollisionUtil.moveToContact(this, a2, CollisionUtil.RIGHT);
			x[1] = 0;
		}
		if (b1 != null) {
			CollisionUtil.moveToContact(this, b1, CollisionUtil.TOP);
			y[1] = 0;
		} 
		if (b2 != null) {
			CollisionUtil.moveToContact(this, b2, CollisionUtil.BOTTOM);
			y[1] = 0;
		}

		super.doKinematics();
	}

	public void jump() {
		super.ySet(Projectile.VELOCITY, -8.5f);
	}
}
