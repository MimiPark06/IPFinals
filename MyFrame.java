import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.io.File;
import javax.sound.sampled.*;

public class MyFrame extends JFrame implements KeyListener{

	Draw drawing;

	public MyFrame(){
		this.drawing = new Draw();
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			drawing.moveUp();
			System.out.println("up");
			System.out.println("pos: " + drawing.x +"," +drawing.y);
	}
	else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			drawing.moveDown();
			System.out.println("down");
			System.out.println("pos: " + drawing.x +"," +drawing.y);
	}
	else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			drawing.moveLeft();
			System.out.println("left");
			System.out.println("pos: " + drawing.x +"," +drawing.y);
	}
	else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			drawing.moveRight();
			System.out.println("right");
			System.out.println("pos: " + drawing.x +"," +drawing.y);
		}
	else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			drawing.attack();
			System.out.println("attack");
		}	
	else if(e.getKeyCode() == KeyEvent.VK_ALT){
			drawing.crouch();
			System.out.println("crouch");
		}
	else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			drawing.bow();
			System.out.println("bow");
		}
	else if(e.getKeyCode() == KeyEvent.VK_Z){
			drawing.kick();
			System.out.println("kick");
		}
	else if(e.getKeyCode() == KeyEvent.VK_S){
			drawing.spawnEnemy();
		}
	else if(e.getKeyCode() == KeyEvent.VK_W){
			drawing.jump();
		}
		drawing.reloadImage();
	}
	public void keyReleased(KeyEvent e){

	}

	public void keyTyped(KeyEvent e){

	}



	public static void main(String[] args) {
		MyFrame gameFrame = new MyFrame();
		gameFrame.setSize(856,480);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.getContentPane().add(gameFrame.drawing);
		gameFrame.addKeyListener(gameFrame);
		System.out.println("Start!");
		try{
				AudioInputStream bg = AudioSystem.getAudioInputStream(new File("Ambience.wav"));

				Clip clip = AudioSystem.getClip();
				clip.open(bg);

				clip.start();

				for (int i=0; i<30000; i++)
				System.out.println("");

			}catch(Exception e){e.printStackTrace();}
	}
}