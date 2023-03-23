package com.attempt.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Trail extends GameObject{
	
	private float alpha=1;
	private Handler handler;
	private Color color;
	private float life;
	
	public Trail(int x, int y,int ancho,int alto,float life, ID id, Color color,Handler handler) {
		super(x, y, id, ancho,alto);
		this.handler=handler;
		this.color=color;
		this.ancho=ancho;
		this.alto=alto;
		this.life=life;
	}

	@Override
	public void tick() {
		if(alpha>life) {
			alpha-=(life-0.001f);
		}else {
			handler.removeObject(this);
		}
		
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d=(Graphics2D)g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		BufferedImage img=null;
		try {
			if (color==Color.blue) {
				img= ImageIO.read(new File("assets/blueBullet.png"));
			} else if (color==Color.green) {
				img= ImageIO.read(new File("assets/greenBullet.png"));
			} else if (color==Color.red) {
				img= ImageIO.read(new File("assets/redBullet.png"));
			}
		} catch(Exception e) {
			System.out.println("Error al encontrar bulletTrail file.");
		}
		g.drawImage(img, x, y, 16*2, 16*2, null);
		//g.fillRect(x,y,16,16);
		g2d.setComposite(makeTransparent(1));
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type=AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type,alpha);
	}
}
