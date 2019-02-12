import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

public class Draw extends JComponent{


	private BufferedImage image;
	private BufferedImage backgroundImage;
	private URL resource = getClass().getResource("run0.png");
	

	//circles's position
	public int x = 30;
	public int y = 280;
	public int height = 0;
	public int width = 0;

	//animation states
	public int state = 0;

	// randomizer
	public Random randomizer;

	// enemy
	public int enemyCount;
	Monster[] monsters = new Monster[10];


	public Draw(){
		randomizer = new Random();
		spawnEnemy();
		

		try{
			image = ImageIO.read(resource);
			backgroundImage = ImageIO.read(getClass().getResource("background.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();

		startGame();
	}
	
	public void startGame(){
		Thread gameThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						for(int c = 0; c < monsters.length; c++){
							if(monsters[c]!=null){
								monsters[c].moveTo(x,y);
								repaint();
							}
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}

	public void spawnEnemy(){
		if(enemyCount < 10){
			monsters[enemyCount] = new Monster(randomizer.nextInt(400), randomizer.nextInt(400), this);
			enemyCount++;
		}
	}

	public void reloadImage(){
		state++;

		if(state == 0){
			resource = getClass().getResource("run0.png");
		}
		else if(state == 1){
			resource = getClass().getResource("run1.png");
		}
		else if(state == 2){
			resource = getClass().getResource("run2.png");
		}
		else if(state == 3){
			resource = getClass().getResource("run3.png");
		}
		else if(state == 4){
			resource = getClass().getResource("run4.png");
		}
		else if(state == 5){
			resource = getClass().getResource("run5.png");
			state = 0;
		}
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void attackAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try{
						if(ctr==4){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("attack"+ctr+".png");
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						repaint();
						Thread.sleep(100);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
	}
	

	public void crouchAnimation(){
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 7; ctr++){
					try{
						if(ctr==6){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("crouch"+ctr+".png");
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						repaint();
						Thread.sleep(100);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
		thread2.start();
	}

	public void jumpAnimation(){
		Thread thread3 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try{
						if(ctr==4){
							resource = getClass().getResource("run0.png");
							y = y +20;
						}
						else{
							resource = getClass().getResource("jump"+ctr+".png");
							y = y - 5;
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						repaint();
						Thread.sleep(100);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
		thread3.start();
	}

	public void bowAnimation(){
		Thread thread4 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 9; ctr++){
					try{
						if(ctr==8){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("bow"+ctr+".png");
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						repaint();
						Thread.sleep(100);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
		thread4.start();
	}

	public void kickAnimation(){
		Thread thread5 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 8; ctr++){
					try{
						if(ctr==7){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("kick"+ctr+".png");
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						repaint();
						Thread.sleep(100);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
		thread5.start();
	}

	public void attack(){
		attackAnimation();
	}

	public void crouch(){
		crouchAnimation();
	}

	public void jump(){
		jumpAnimation();
	}

	public void bow(){
		bowAnimation();
	}

	public void kick(){
		kickAnimation();
	}

	public void moveUp(){
		//y = y - 5;
		//reloadImage();
		//repaint();
		jumpAnimation();
	}

	public void moveDown(){
		y = y + 5;
		reloadImage();
		repaint();
	}

	public void moveLeft(){
		x = x - 5;
		reloadImage();
		repaint();
	}

	public void moveRight(){
		x = x + 5;
		reloadImage();
		repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.drawImage(backgroundImage, 0,0, this);
		g.drawImage(image, x,y, this);

	for(int c = 0; c < monsters.length; c++){
			if(monsters[c]!=null){
				g.drawImage(monsters[c].image, monsters[c].xPos, monsters[c].yPos, this);
				g.setColor(Color.GREEN);
				g.fillRect(monsters[c].xPos+7, monsters[c].yPos, monsters[c].life, 2);
		}
	}
}
}
