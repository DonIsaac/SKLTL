package skltl.toolkit;

import java.util.ArrayList;

public class StopWatch {
	private boolean isStarted;
	private long startTime;
	private long endTime;
	private ArrayList<Long> benchmarks;
	
	public StopWatch(){
		benchmarks = new ArrayList<Long>();
		isStarted=false;
		startTime=0L;
		endTime=0L;
	}
	public void start(){
		reset();
		startTime=System.currentTimeMillis();
		isStarted=true;
	}
	public void end(){
		if(isStarted){
		endTime=System.currentTimeMillis();
		isStarted=false;
		}else{
			throw new IllegalStateException("You must call start() before you can call stop()");
		}
	}
	public void addBenchmark(){
		if(isStarted)
		benchmarks.add(System.currentTimeMillis()-startTime);
		else
			throw new IllegalStateException("This method cannot be called before the StopWatch has been started.");
	}
	public long getBenchmark(int index){
		return benchmarks.get(index);
	}
	public long[] getBenchmarks(){
		long[] bm = new long[benchmarks.size()];
		 for(int i=0;i<benchmarks.size();i++){
			 bm[i]=benchmarks.get(i);
		 }
		 return bm;
	}
	public float getDuration(){
		if(startTime!=0L&&endTime!=0L&&!isStarted)
		return (startTime-endTime)/1000f;
		else
			throw new IllegalStateException("You must complete a start/stop cycle before you can call this method.");
	}
	public void reset(){
		if(!isStarted){
		startTime=0L;
		endTime=0L;
		benchmarks.clear();
		}else
			throw new IllegalStateException("You cannot call this method while the StopWatch is running.");
	}
}
