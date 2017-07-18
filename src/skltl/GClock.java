package skltl;

/**
 * A class used for managing different time calculations.
 */
public class GClock {
	private float err;
	private long startTime;
	private long dt;
	private long lastTime;
	private float targetTime;
	private boolean isFpsCapped;

	protected GClock() {
		startTime = lastTime = System.currentTimeMillis();
		setTargetFPS(60);
		isFpsCapped = true;

	}

	/**
	 * Updates the <code>GClock</code> for use during <code>Game</code>'s main
	 * game loop.
	 */
	protected void update() {
		this.dt = System.currentTimeMillis() - lastTime;
		this.lastTime = System.currentTimeMillis();
	}

	/**
	 * Returns the amount of time the last main loop took in seconds. It is the
	 * equivalent of ∆t. Multiply this value by any time dependent rates to get
	 * units/second. <br />
	 * <br />
	 * The following code will increase x's value by 4 units per second:
	 * 
	 * <pre>
	 * float x = 0f;
	 * float speed = 4f;
	 * x += speed * clock.getElapsedTime();
	 * </pre>
	 * 
	 * @return time elapsed during last main loop in seconds.
	 */
	public float getElapsedTime() {
		return (float) dt / 1000f;

	}

	/**
	 * <i>DON'T CALL THIS METHOD!!</i>
	 */
	public float getTotalTime() {
		return (float) (System.currentTimeMillis() - startTime) / 1000f;
	}

	/**
	 * <i>DON'T CALL THIS METHOD!!</i>
	 * 
	 * @param FPS
	 */
	protected void setTargetFPS(int FPS) {
		if (FPS > 0) {
			targetTime = 1f / (float) FPS;
			err = targetTime - (1f / (float) FPS + 2.5f);
		} else
			throw new IllegalArgumentException("You cannot have negative FPS");
	}

	/**
	 * <i>DON'T CALL THIS METHOD!!</i>
	 */
	protected int getTargetFPS() {
		return (int) ((1f / targetTime) + .5f);
	}

	/**
	 * Whether or not the Game is running slower than the target FPS, with error
	 * accounted for.
	 * 
	 * @return true if the game is running slowly, false otherwise.
	 */
	public boolean isRunningSlowly() {
		return getElapsedTime() > targetTime + err;
	}

	/**
	 * <i>DON'T CALL THIS METHOD!!</i>
	 */
	protected boolean isFpsCapped() {
		return isFpsCapped;
	}

	/**
	 * <i>DON'T CALL THIS METHOD!!</i>
	 */
	protected void setFpsCapped(boolean isFpsCapped) {
		this.isFpsCapped = isFpsCapped;
	}

	/**
	 * <i>DON'T CALL THIS METHOD!!</i>
	 */
	protected long calculateSleepTime() {
		long extraSleep = (long) (targetTime * 1000f)
				- (System.currentTimeMillis() - lastTime);
		return extraSleep >= 0 ? (long) (extraSleep) : 0;
	}

}
