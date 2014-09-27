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
		Block a3 = PlatformDemo.col.blockAt(x[1] + x[0], y[0] + height - 1);
		Block a4 = PlatformDemo.col.blockAt(x[1] + x[0] + width, y[0] + height - 1);


		Block b1 = PlatformDemo.col.blockAt(x[0], y[0] + y[1]);
		Block b2 = PlatformDemo.col.blockAt(x[0], y[0] + y[1] + height);
		Block b3 = PlatformDemo.col.blockAt(x[0] + width, y[0] + y[1]);
		Block b4 = PlatformDemo.col.blockAt(x[0] + width, y[0] + y[1] + height);
		
		if (a1 != null) {
			System.out.print("LEFT ");
			CollisionUtil.moveToContact(this, a1, CollisionUtil.LEFT);
			x[1] = 0;
		}
		else if (a2 != null) {
			System.out.print("RIGHT ");
			CollisionUtil.moveToContact(this, a2, CollisionUtil.RIGHT);
			x[1] = 0;
		}
		else if (b1 != null) {
			System.out.print("TOP  ");
			CollisionUtil.moveToContact(this, b1, CollisionUtil.TOP);
			y[1] = 0;
		} 
		else if (b2 != null) {
			System.out.print("BOTTOM ");
			CollisionUtil.moveToContact(this, b2, CollisionUtil.BOTTOM);
			y[1] = 0;

		}
		else if (a3 != null) {
			System.out.print("LEFTa3 ");
			CollisionUtil.moveToContact(this, a3, CollisionUtil.LEFT);
			x[1] = 0;
		}
		else if (a4 != null) {
			System.out.print("RIGHTa4");
			CollisionUtil.moveToContact(this, a4, CollisionUtil.RIGHT);
			x[1] = 0;
		}
		else if (b3 != null) {
			System.out.print("UPb3 ");
			CollisionUtil.moveToContact(this, b3, CollisionUtil.TOP);
			y[1] = 0;

		} 
		else if (b4 != null) {
			System.out.print("BOTTOMb4 ");
			CollisionUtil.moveToContact(this, b4, CollisionUtil.BOTTOM);
			y[1] = 0;
		}
		System.out.println();
		super.doKinematics();
	}

	public void jump() {
		super.ySet(Projectile.VELOCITY, -8.5f);
	}
}
