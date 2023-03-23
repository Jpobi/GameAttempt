package com.attempt.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.module.Configuration;
import java.util.Random;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 3874260157205305794L;

	private Configuration config;
	
	public static final int ancho= 640, alto=(ancho/12)*9;
	
	private Thread thread;
	private boolean running=false;
	
	private Handler handler;
	
	private Random r;
	
	private HUD hud;
	public Game() {
		this.handler=new Handler();
		
		this.addKeyListener(new KeyInput(handler));
		
		this.hud=new HUD();
		
		new Window(ancho,alto,"First Game",this);
		
		r=new Random();
		//handler.addObject(new Player(ancho/2-100,alto/2-32, ID.Player,handler,32,32));
		//handler.addObject(new Player(ancho/2-80,alto/2-32, ID.Player2,32,32));
		//for(int i=0;i<5;i++) {
			//handler.addObject(new BasicEnemy(r.nextInt(ancho),r.nextInt(alto), ID.BasicEnemy,handler,26,26));
		//}
		handler.addObject(new Tank(ancho/2-300,alto/2-32, ID.Tank1,handler,40,40));
		handler.addObject(new Tank(ancho/2+250,alto/2-32, ID.Tank2,handler,40,40));
		handler.addObject(new TankEnemy(ancho/2-20,alto/2-70, ID.TankEnemy,handler,40,40));
		handler.addObject(new Wall(ancho/2+150,alto/2-150,ID.Wall,70,300));
		handler.addObject(new Wall(ancho/2-210,alto/2-150,ID.Wall,70,300));
	}
	
	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		running=true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running=false;
		}catch(Exception error) {
			error.printStackTrace();
		}
	}
	
	public void run() {
		//HERE GOES THE GAME LOOP, (AKA MAGIC VARIABLE FPS, RUNTIME, GAMESTATEUPDATING, & DISPLAYUPDATING STUFF)
		this.requestFocus();
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
        	long now = System.nanoTime();
        	delta += (now - lastTime) / ns;
        	lastTime = now;
        	while(delta >=1) {
        		tick();
        		delta--;
        	}
        	if(running) {
        		render();
        	}
        	frames++;
        	
        	if(System.currentTimeMillis() - timer > 1000) {
        		timer += 1000;
        		System.out.println("FPS: "+ frames);
        		frames = 0;
        	}
        }
        stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
		if (HUD.vida1<=0) {
		//	HUD.points+=1;
		//} else {
			System.out.println("Game Over. Gano P2");
			System.exit(1);
		}
		if (HUD.vida2<=0) {
			System.out.println("Game Over. Gano P1");
			System.exit(1);
		}
	}
	
	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if (bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g=bs.getDrawGraphics();
		
		BufferedImage img=null;
		try {
			img= ImageIO.read(new File("assets/background.png"));
		} catch(Exception e) {
			System.out.println("Error al encontrar background.png.");
		}
		g.setColor(Color.black);
		//g.fillRect(0, 0, ancho, alto);
		g.drawImage(img,0, 0, ancho, alto,null);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int val,int max,int min) {
		if(val>max) {
			return val=max;
		} else if(val<min) {
			return val=min;
		} else {
			return val;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}
}
