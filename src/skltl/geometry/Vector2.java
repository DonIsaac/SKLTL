package skltl.geometry;

public class Vector2 {

	public float x;
	public float y;

	public static final Vector2 zero = new Vector2(0f, 0f);

	public Vector2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public void normalize() {
		float len = length();
		this.x /= len;
		this.y /= len;
	}

	public void set(float nx, float ny) {
		x = nx;
		y = ny;
	}

	@Override
	public Vector2 clone() {
		return new Vector2(x, y);
	}

	public Vector3 toVector3() {
		return new Vector3(x, y, 0);
	}

	@Override
	public String toString() {
		return "<" + x + "," + y + ">";
	}

}
