package com.attempt.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Wall extends GameObject{
	
	
	public Wall(int x, int y, ID id,int ancho, int alto) {
		super(x, y, id, ancho, alto);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
				
		BufferedImage img=null;
		try {
			img= ImageIO.read(new File("assets/wall.jpg"));
		} catch(Exception e) {
			System.out.println("Error al encontrar wall.jpg.");
		}
		
		g.setColor(Color.gray);
		//g.fillRect(x, y, ancho, alto);
		g.drawImage(img,x, y, ancho, alto,null);
		g.setColor(Color.red);
		//BORDES
		/*g.drawRect(x, y, ancho,2 );
		g.drawRect(x, y, 2,alto);
		g.drawRect(x+ancho-2,y,2,alto);
		g.drawRect(x,y+alto-2,ancho,2);*/
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(x,y,ancho,alto);
	}
	public int collidesWith(GameObject o) { //ver caso ambos horizontal y vertical=3
		int a=0,b=0;
		if ((o.getBounds().intersects(new Rectangle(x,y,1,alto))) || (o.getBounds().intersects(new Rectangle(x+ancho-1,y,1,alto)))) {
			a=1; //1=horizontal
		}
		if ((o.getBounds().intersects(new Rectangle(x,y,ancho,1))) || (o.getBounds().intersects(new Rectangle(x,y+alto-1,ancho,1)))) { 
			b=2; //2=vertical
		}
		if(a==1 && b==2) {
			return 3; //ambos
		} else if(a!=0) {return a;};
		return b;
	}
}
