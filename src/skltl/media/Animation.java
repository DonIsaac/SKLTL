package skltl.media;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private ArrayList<Image> animation;
	private float time;
	private long startTime=0;
	
	private float timePercent;
	
	public Animation(float lengthOfAnimation,Image... animation){
		this.animation = new ArrayList<Image>();
		this.time=lengthOfAnimation;
		for(Image a: animation){
			this.animation.add(a);
		}
	}
	public void add(Image image){
		animation.add(image);
	}
	
	public void setAnimationLength(float nt){
		this.time=nt;
	}
	public float getAnimationLength(){
		return this.time;
	}
	public void reset(){
		startTime=0;
	}
	public Image draw(){
		 timePercent=(System.currentTimeMillis()-startTime)/(time*1000f);
		if(startTime==0)
			startTime=System.currentTimeMillis();
		if(timePercent<1f){
			return animation.get((int)timePercent*animation.size());
		}else{
			startTime=System.currentTimeMillis();
			return animation.get((int)((System.currentTimeMillis()-startTime)/(time*1000f)*animation.size()));
		}
		
		
	}
}
