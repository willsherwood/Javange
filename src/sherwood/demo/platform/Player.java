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
        // will's code
        /*
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
        */
       // vertical
       if (y[1] != 0) {
           // find x range
           int minx = (int)(x[0]);
           int maxx = (int)Math.ceil(x[0]) width;
           int dy = (int)Math.signum(y[1]) * (height Block.BLOCK_SIZE - 1);
           double absv = Math.abs(y[1]);
           int yy = (int)y[0];
           boolean coll = false;
           mainloop:
           while (Math.abs(yy - y[0]) < absv) {
               System.out.println(yy - y[0]);
               for (int xx = minx; xx < maxx; xx += Block.BLOCK_SIZE) {
                   Block b;
                   if ((b = PlatformDemo.col.blockAt(xx, yy)) != null) {
                       // collision
                       coll = true;
                       if (y[1] < 0) {
                           y[0] = b.y[0] b.height;
                       } else {
                           y[0] = b.y[0] - height;
                       }
                   }
               }
               yy += dy;
           }
       }
       super.doKinematics();
	}

	public void jump() {
		super.ySet(Projectile.VELOCITY, -8.5f);
	}
}
