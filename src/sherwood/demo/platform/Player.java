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

		Block tl = PlatformDemo.col.blockAt(x[0]+x[1], y[0]+y[1]);
		Block tr = PlatformDemo.col.blockAt(x[0]+x[1]+width, y[0]+y[1]);
		Block bl = PlatformDemo.col.blockAt(x[0]+x[1], y[0]+y[1]+height);
		Block br = PlatformDemo.col.blockAt(x[0]+x[1]+width, y[0]+y[1]+height);
		
		if (tl != null) {
			if (y[1] > 0 || y[0] < tl.height + tl.y[0]) {
				CollisionUtil.moveToContact(this, tl, CollisionUtil.LEFT);
				x[1] = 0;
			}
			else {
				CollisionUtil.moveToContact(this, tl, CollisionUtil.TOP);
				x[1] = y[1] = 0;
			}
		} if (bl != null) {
			if (y[1] > 0 && y[0] + height -1 < bl.y[0]) {
				CollisionUtil.moveToContact(this, bl, CollisionUtil.BOTTOM);
				y[1] = 0;
			}
			else {
				CollisionUtil.moveToContact(this, bl, CollisionUtil.LEFT);
				x[1] = 0;
			}
		}

		System.out.println();
		super.doKinematics();
	}

	public void jump() {
		super.ySet(Projectile.VELOCITY, -8.5f);
	}
}
