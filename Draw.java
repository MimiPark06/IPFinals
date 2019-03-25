import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.io.*;
import java.io.File;
import javax.sound.sampled.*;


public class Draw extends JComponent{

	private BufferedImage image;
	private BufferedImage backgroundImage;
	public URL resource = getClass().getResource("run0.png");
	

	//CIRCLE'S POSITION
	public int x = 30;
	public int y = 280;
	public int height = 0;
	public int width = 0;
	public int life = 100;
	//movement of the hero
	public boolean brun = false;
	public boolean right = true;
	public boolean notMoving = true;

	//animation states
	public int states = 0;
	public int states1 = 0;

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
			monsters[enemyCount] = new Monster(randomizer.nextInt(250), randomizer.nextInt(100), this);
			enemyCount++;
		}
	}

	public void reloadImage(){

		states++;

		if(states == 0){
			resource = getClass().getResource("run0.png");
		}
		else if(states == 1){
			resource = getClass().getResource("run1.png");
			
		} 
		else if(states == 2){
			resource = getClass().getResource("run2.png");
			
		}
		else if(states == 3){
			resource = getClass().getResource("run3.png");
			
		}
		else if(states == 4){
			resource = getClass().getResource("run4.png");
			
		}
		else if(states == 5){
			resource = getClass().getResource("run5.png");
			states = 0;
		}

		try{
			image = ImageIO.read(resource);

		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void reloadImage1(){

		states1++;

		if(states1 == 0){
			resource = getClass().getResource("left(0).png");
		}
		else if(states1 == 1){
			resource = getClass().getResource("left(1).png");
			
		} 
		else if(states1 == 2){
			resource = getClass().getResource("left(2).png");
			
		}
		else if(states1 == 3){
			resource = getClass().getResource("left(3).png");
			
		}
		else if(states1 == 4){
			resource = getClass().getResource("left(4).png");
			
		}
		else if(states1 == 5){
			resource = getClass().getResource("left(5).png");
			states1 = 0;
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
				for(int x=0; x<monsters.length; x++){
					if(monsters[x]!=null){
						if(monsters[x].contact){
							monsters[x].life = monsters[x].life - 15;
						}
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
						Thread.sleep(200);
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
		repaint();
		checkCollision();
	}

	public void moveRight(){
		x = x + 5;
		notMoving = false;
		right = true;
		reloadImage();
		repaint();
		checkCollision();
	}

	public void moveLeft(){
		x = x - 5;
		right = false;
		notMoving = false;
		repaint();
		reloadImage1();
		checkCollision();
	}

	public void moveUp(){
		y = y - 5;
		repaint();
		checkCollision();

	}

	public void moveDown(){
		y = y + 5;
		reloadImage();
		repaint();
		checkCollision();
	}

	public void crouch(){
		crouchAnimation();
		checkCollision();
	}

	public void jump(){
		jumpAnimation();
		checkCollision();
	}

	public void bow(){
		bowAnimation();
		checkCollision();
	}

	public void kick(){
		kickAnimation();
		checkCollision();
	}

	public void checkCollision(){
		int xChecker = x + width;
		int yChecker = y;

		for(int x=0; x<monsters.length; x++){
			boolean collideX = false;
			boolean collideY = false;

			if(monsters[x]!=null){
				monsters[x].contact = false;

				if(yChecker > monsters[x].yPos){
					if(yChecker-monsters[x].yPos < monsters[x].height){
						collideY = true;
						System.out.println("collideY");
					}
				}
				else{
					if(monsters[x].yPos - (yChecker+height) < monsters[x].height){
						collideY = true;
						System.out.println("collideY");
					}
				}

				if(xChecker > monsters[x].xPos){
					if((xChecker-width)-monsters[x].xPos < monsters[x].width){
						collideX = true;
						System.out.println("collideX");
					}
				}
				else{
					if(monsters[x].xPos-xChecker < monsters[x].width){
						collideX = true;
						System.out.println("collideX");
					}
				}
			}

			if(collideX && collideY){
				System.out.println("collision!");
				monsters[x].contact = true;
			}
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(image,x,y,this);
		g.setColor(Color.WHITE);
		g.fillRect(x+15,y,25,2);
		g.setColor(Color.RED);
		g.fillRect(x+15,y+2,10,2);

		for(int c = 0; c < monsters.length; c++){
			if(monsters[c]!=null){
				g.drawImage(monsters[c].image, monsters[c].xPos, monsters[c].yPos, this);
				g.setColor(Color.GREEN);
				g.fillRect(monsters[c].xPos+7, monsters[c].yPos, monsters[c].life, 2);
			}	
		}

	}

	public void checkDeath(){
		for(int c = 0; c < monsters.length; c++){
			if(monsters[c]!=null){
				if(!monsters[c].alive){
					monsters[c] = null;
				}
			}			
		}
	}

}