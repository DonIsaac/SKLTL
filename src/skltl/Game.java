package skltl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import skltl.media.ContentManager;

/**
 * An XNA like framework for making games. Once you inherit this class, use the
 * inherited methods to write your game.
 * 
 * @version 1.0.0
 * @author © Epichunter 2016 All Rights Reserved
 */
public abstract class Game extends JPanel implements Runnable {

	
	private static final long serialVersionUID = 4175428857087362477L;
	private Graphics2D g;
	private Image renderImage = null;
	private GameFrame f;
	private GClock clock;
	private ContentManager content;
	private Thread thread = null;

	/**
	 * Whether or not the game is paused. Use this to implement pausing into
	 * your game.
	 */
	public boolean isPaused;
	/**
	 * Whether or not update() will be called if paused. Default is true.
	 */
	public boolean willUpdateOnPause = true;
	/**
	 * Whether or not the Game's window is resizable. Modification of this field
	 * after the game has been initialized will do nothing. Default is false.
	 */
	public boolean isResizable = false;
	/**
	 * Whether or not the Game will pause when it is no longer the active window
	 * (i.e, if you tab out). Default is true.
	 */
	public boolean willPauseOnDeactivation = true;
	/**
	 * Whether or not the Game will unpause when it becomes the active window
	 * again. Default is true.
	 */
	public boolean willUnpauseOnActivation = true;
	private boolean isRunning;
	protected boolean canModifyFrame = true;

	private int width = 800, height = 500;

	/**
	 * Makes a new Game framework.
	 * 
	 * @param title
	 *            the title of your game
	 */
	public Game(String title) {
		content = new ContentManager(this);
		this.loadContent(content);
		this.initialize();
		f = new GameFrame(width, height, isResizable, title, this);

		setFocusable(true);
		requestFocus();

	}

	public Game() {
		content = new ContentManager(this);
		this.loadContent(content);
		this.initialize();
		f = new GameFrame(width, height, isResizable,
				this.getClass().getName(), this);

		setFocusable(true);
		requestFocus();

	}

	/**
	 * <i>DO NOT CALL THIS METHOD. THIS METHOD SHOULD ONLY BE USED BY THE
	 * FRAMEWORK.</i>
	 */
	public void run() {
		clock = new GClock();
		while (isRunning) {
			clock.update();
			if (!isPaused || willUpdateOnPause)
				this.update(clock);

			this.render(clock);
			this.paintScreen();

			if (clock.isFpsCapped()) {
				// thread.sleep(millis);
				try {
					Thread.sleep(clock.calculateSleepTime());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}// end of if statement
		}
	}

	/**
	 * Sets the width of the game. This cannot be called after initialize() and
	 * loadContent() have run.
	 * 
	 * @param width
	 *            width of the game
	 */
	public void setWidth(int width) {
		if (canModifyFrame) {
			this.width = width;
			// f.setWidth(width);
		}

	}

	/**
	 * Returns the Game's width
	 * 
	 * @return the Game's width
	 */
	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the height of the game. This cannot be called after initialize() and
	 * loadContent() have run.
	 * 
	 * @param height
	 *            hight of the game
	 */
	public void setHeight(int height) {
		if (canModifyFrame) {
			this.height = height;
			// f.setHeight(height);
		}
	}

	/**
	 * Returns the Game's height.
	 * 
	 * @return the Game's height
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * @return whether or not you can modify the frame
	 */
	public boolean isFrameModifyable() {
		return this.canModifyFrame;
	}

	/**
	 * Sets the target FPS for the game. The game will only attempt to stay at
	 * this targeted FPS if isFpsCaped is true. Default is 60 FPS.
	 * 
	 * @param FPS
	 *            new target FPS
	 */
	public void setTargetFPS(int FPS) {
		clock.setTargetFPS(FPS);
	}

	/**
	 * Returns the target FPS for the game. Default is 60 FPS.
	 * 
	 * @return the target FPS
	 */
	public int getTargetFPS() {
		return clock.getTargetFPS();
	}

	/**
	 * Returns the value of isFpsCapped.
	 * 
	 * @return true if the FPS is capped, false if it is not
	 */
	public boolean isFpsCapped() {
		return clock.isFpsCapped();
	}

	/**
	 * Allows you to cap/uncap the FPS. Default is true.
	 * 
	 * @param isFpsCapped
	 *            the new FPS cap
	 */
	public void setFpsCapped(boolean isFpsCapped) {
		clock.setFpsCapped(isFpsCapped);
	}

	/**
	 * This is the method where you should load of your media (images, audio
	 * clips, etc.)
	 * 
	 * @param content
	 *            your Game's ContentManager
	 */
	public abstract void loadContent(ContentManager content);

	/**
	 * This is the method where you should release all un-needed resources and
	 * do anything else your game might need done right before it exits (EX:
	 * autosave).
	 */
	public abstract void unloadContent(ContentManager content);

	/**
	 * This is the method where you should initialize all your variables and
	 * objects.
	 * 
	 * @param content
	 *            your Game's ContentManager
	 */
	public abstract void initialize();

	/**
	 * This is the method where you should update any of your game logic.
	 * 
	 * @param clock
	 *            information about the Game's time.
	 */
	public abstract void update(GClock clock);

	/**
	 * This is the method where you should draw your game.
	 * 
	 * @param g
	 *            the Graphics tool you should use to draw your game
	 * @param clock
	 *            information about the Game's time
	 */
	public abstract void draw(Graphics2D g, GClock clock);

	private void render(GClock clock) {
		if (renderImage == null) {
			renderImage = createImage(width, height);
			if (renderImage == null) {
				return;
			} else
				g = (Graphics2D) renderImage.getGraphics();
		}
		if (renderImage == null) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
		} else
			g.drawImage(renderImage, 0, 0, this);
		if (g != null) {
			this.draw(g, clock);
		}
	}

	private void paintScreen()
	// use active rendering to put the buffered image on-screen
	{
		Graphics g;
		try {
			g = this.getGraphics();
			// System.out.println(g);
			if ((g != null) && (renderImage != null))
				g.drawImage(renderImage, 0, 0, null);
			// Sync the display on some systems.
			// (on Linux, this fixes event queue problems)
			Toolkit.getDefaultToolkit().sync();
			if (g != null)
				g.dispose();
		} catch (Exception e) {
			System.out.println("Graphics context error: " + e);
			e.printStackTrace();
		}
	} // end of paintScreen()

	protected synchronized void start() {
		if (thread == null || !isRunning) {
			thread = new Thread(this);
			thread.setDaemon(true);
			isRunning = true;
			isPaused = false;
			thread.start();
		}

	}

	/**
	 * Call this method to stop the game. <i>DO NOT USE System.exit()!</i>
	 */
	public synchronized void stop() {
		isRunning = false;
		unloadContent(content);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		f.dispose();
		System.exit(0);
	}	

}
