package sherwood.demo.platform;

public abstract class Projectile {

	protected float[] x = {0, 0, 0};
	protected float[] y = {0, 0, 0};
	
	public static final int POSITION = 0, VELOCITY = 1, ACCELERATION = 2;
	
	protected void doKinematics(float maxVelocity, float minVelocity) {
		doKinematics();
		if (y[1] > maxVelocity)
			y[1] = maxVelocity;
		if (y[1] < minVelocity)
			y[1] = minVelocity;
		
	}
	
	protected void doKinematics() {
		x[0] += x[1] += x[2];
		y[0] += y[1] += y[2];
	}

	public void ySet(int property, float newValue) {
		y[property] = newValue;
	}

	public void xSet(int property, float newValue) {
		x[property] = newValue;
	}
	
	public float yGet(int property) {
		return y[property];
	}

	public float xGet(int property) {
		return x[property];
	}
}
