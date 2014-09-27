package sherwood.demo.platform;

public class Entity extends Projectile {

	protected int width, height;

	public Entity(float x, float y, int width, int height) {
		super.xSet(POSITION, x);
		super.ySet(POSITION, y);
		this.width = width;
		this.height = height;
	}

	public Entity(Entity b) {
		this(b.xGet(POSITION), b.yGet(POSITION), b.width, b.height);
	}

	public void step() {
		super.doKinematics(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
	}

	public void step(float max, float min) {
		super.doKinematics(max, min);
	}
}
