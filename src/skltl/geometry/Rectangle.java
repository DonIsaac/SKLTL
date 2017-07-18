package skltl.geometry;

public class Rectangle {
	public Vector2 pos;
	public float width, height;

	public Rectangle(Vector2 pos, float width, float height) {
		this.pos = pos;
		this.width = width;
		this.height = height;
	}

	public Rectangle(float x, float y, float width, float height) {
		this.pos = new Vector2(x, y);
		this.width = width;
		this.height = height;
	}
	/**
	 * Tests to see if a <code>Vector2</code> object is inside of this <code>Rectangle</code>.
	 * @param v the <code>Vector2</code> to test
	 * @return true if <b>v</b> is inside the <code>Rectangle</code>, false otherwise.
	 */
	public boolean contains(Vector2 v) {
		 return this.pos.x < v.x && 
				this.pos.x + this.width > v.x&& 
				this.pos.y < v.y && 
				this.pos.y + this.height > v.y;
	}
	public boolean intersects(Rectangle r){
		//this may or may not work
		return pos.x < r.pos.x + r.width && pos.x + width > r.pos.x && pos.y < r.pos.y + r.height && pos.y + height > r.pos.y;
		
	}
	public Vector2 getCenter(){
		return new Vector2(pos.x+width/2,pos.y+height/2);
	}
	/**
	 * Returns the aspect ratio of this <code>Rectangle</code> in the format "VALUE:1".
	 * @return the aspect ratio of this <code>Rectangle</code>
	 */
	public float getAspectRatio() {
		return width / height;
	}
}
