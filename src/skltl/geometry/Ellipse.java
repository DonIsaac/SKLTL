package skltl.geometry;

public class Ellipse {
	Vector2 center;
	float xRadius,yRadius;
	
	public Ellipse(float x, float y, float xRadius, float yRadius){
		this.center=new Vector2(x,y);
		this.xRadius=xRadius;
		this.yRadius=yRadius;
	}
	public Ellipse(Vector2 center, float xRadius, float yRadius){
		this.center = new Vector2(center.x-xRadius,center.y-yRadius);
		this.xRadius=xRadius*2;
		this.yRadius=yRadius*2;
	}
	public Ellipse(Vector2 center, float radius){
		this.center=center;
		this.xRadius=this.yRadius=radius;
	}
	public boolean intersects(Vector2 v){
		return ((v.x-center.x)*(v.x-center.x))/(xRadius*xRadius)+((v.y-center.y)*(v.y-center.y))/(yRadius*yRadius)<=1f;
	}
	public boolean intersects(float x, float y){
		return ((x-center.x)*(x-center.x))/(xRadius*xRadius)+((y-center.y)*(y-center.y))/(yRadius*yRadius)<=1f;

	}
	public boolean intersects(Ellipse e){
		//return VectorMath.distance(center, e.center);
		return false;
	}
	public boolean intersects(Rectangle r){
		return false;
	}
	public float radiusAt(float theta){
		return (float)Math.sqrt((xRadius*Math.cos(theta))*(xRadius*Math.cos(theta))+(yRadius*Math.sin(theta))*(yRadius*Math.sin(theta)));
	}
	public float radiusAt(Vector2 v){
		Vector2 t = new Vector2(v.x-center.x,v.y-center.y);
		t.normalize();
		float theta= (float) Math.atan(t.y/t.x);
		return (float)Math.sqrt((xRadius*Math.cos(theta))*(xRadius*Math.cos(theta))+(yRadius*Math.sin(theta))*(yRadius*Math.sin(theta)));
	}
}
