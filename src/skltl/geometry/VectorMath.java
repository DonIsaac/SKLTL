package skltl.geometry;

public class VectorMath {
	//Vector2 methods
	public static Vector2 add(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x+v2.x,v1.y+v2.y);
	}
	public static Vector2 subtract(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x-v2.x,v1.y-v2.y);
	}
	public static Vector2 scale(Vector2 v, float factor){
		return new Vector2(v.x*factor,v.y*factor);
	}
	public static float distance(Vector2 v1, Vector2 v2){
		return subtract(v1,v2).length();
	}
	public static float dotProduct(Vector2 v1, Vector2 v2){
		return ((v1.x * v2.x + v1.y * v2.y));
	}
	//Vector3 methods

}
