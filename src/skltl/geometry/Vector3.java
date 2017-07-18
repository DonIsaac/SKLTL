package skltl.geometry;

public class Vector3 {
	public float x;
	public float y;
	public float z;

	public static Vector3 zero = new Vector3(0.0f, 0.0f, 0.0f);
	public static Vector3 right = new Vector3(1.0f, 0.0f, 0.0f);
	public static Vector3 up = new Vector3(0.0f, 1.0f, 0.0f);
	public static Vector3 forward = new Vector3(0.0f, 0.0f, 1.0f);
	public static Vector3 left = new Vector3(-1.0f, 0.0f, 0.0f);
	public static Vector3 down = new Vector3(0.0f, -1.0f, 0.0f);
	public static Vector3 backwards = new Vector3(0.0f, 0.0f, -1.0f);

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float length() {

		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Turns the vector into a unit vector
	 */
	public void normalize() {
		float len = length();
		try {
			this.x = x / len;
			this.y = y / len;
			this.z = z / len;
		} catch (ArithmeticException e) {
			System.err.println("You can't normalize <0,0,0> you dumb bitch");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean isUnitVector() {
		return length() == 1f;
	}

	/**
	 * Gets the distance from this vector to another vector
	 * 
	 * @param v
	 *            the vector being checked
	 * @return the distance between this vector and v
	 */
	public float distance(Vector3 v) {
		float dx = this.x - v.x;
		float dy = this.y = v.y;
		float dz = this.z - v.z;
		return (float) Math.sqrt(dx * dx + dy + dy + dz * dz);
	}

	public float dotProduct(Vector3 v) {
		return ((x * v.x + y * v.y + z * v.z));
	}

	public void crossProduct(Vector3 v) {
		float tmpx = y * v.z - z * v.y, tmpy = z * v.x - x * v.z, tmpz = x
				* v.y - y * v.x;
		x = tmpx;
		y = tmpy;
		z = tmpz;
	}

	public void set(float nx, float ny, float nz) {
		x = nx;
		y = ny;
		z = nz;
	}

	public void multiply(float factor) {
		x *= factor;
		y *= factor;
		z *= factor;
	}

	public void add(Vector3 v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}

	public void subtract(Vector3 v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}

	public Vector2 toVector2(){
		return new Vector2(x,y);
	}
	@Override
	public String toString() {
		return "<" + x + "," + y + "," + z + ">";
	}

}
