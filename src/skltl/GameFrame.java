package skltl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

class GameFrame extends JFrame implements WindowListener{

	
	private static final long serialVersionUID = 1945982376860126174L;
	private Game game;
	
	public GameFrame(int width, int height, boolean resizable, String name, Game game){
		super(name);
		this.game=game;	
		game.canModifyFrame=false;
		this.setSize(width,height);
		this.addWindowListener(this);
		this.setResizable(resizable);
		this.setLocationRelativeTo(null);
		this.add(game);
		game.start();
		this.setVisible(true);
	}

	public synchronized void stop(){
		game.stop();
		this.dispose();
		
	}
	public void windowActivated(WindowEvent e) {
		game.isPaused=false;
		
	}
	public void windowClosed(WindowEvent e) {
		this.stop();
		
	}
	public void windowClosing(WindowEvent e) {
		this.stop();
		
	}
	public void windowDeactivated(WindowEvent e) {
		if(game.willUnpauseOnActivation)
		game.isPaused=true;
		
	}
	public void windowDeiconified(WindowEvent e) {
		if(game.willPauseOnDeactivation)
		game.isPaused=false;
		
	}
	public void windowIconified(WindowEvent e) {
		game.isPaused=true;
		
	}
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
