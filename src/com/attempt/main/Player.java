package com.attempt.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	//private Random r;
	private Handler handler;
	private double tilt=0;
	
	public Player(int x, int y, ID id,Handler handler,int ancho,int alto) {
		super(x, y, id, ancho,alto);
		this.handler=handler;
		//r=new Random();
		//velocX=r.nextInt(4)+1;
		//velocY=r.nextInt(4);
	}

	public void tick() {
		x+=velocX;
		y+=velocY;
		
		collision();
	}
	public void render(Graphics g) {
		
		//Color rastros
		if(id==ID.Player) {g.setColor(Color.red);}
		else {g.setColor(Color.white);};
		
		Graphics2D g2d=(Graphics2D)g;
		g2d.setColor(g.getColor());
		
		double ancho2=x+16;
		double alto2=y+16;
		
		if(velocX==0 || velocY==0) {
			if (velocX==0 && velocY==0) {
				g2d.rotate(tilt,ancho2,alto2); //rotate (ángulo, valor X del eje de rot,valor y de eje de rot)
				g.fillRect(x+32, y+12, 15, 10);
				g2d.rotate(-tilt,ancho2,alto2);
			}
			if(velocX>0) { //Derecha
				for (int i=velocX;i>0;i--) {
					g.drawRect(x-i, y, 32, 32);
				}
				tilt=0;
				g2d.rotate(tilt,ancho2,alto2);
				g.fillRect(x+32, y+12, 15, 10);
				g2d.rotate(-tilt,ancho2,alto2);
			} else
			if(velocX<0) { //Izquierda
				for (int i=velocX;i<0;i++) {
					g.drawRect(x-i, y, 32, 32);
				}
				tilt=Math.PI;
				g2d.rotate(tilt,ancho2,alto2);
				g.fillRect(x+32, y+12, 15, 10);
				g2d.rotate(-tilt,ancho2,alto2);
			}
			if (velocY>0) { //abajo
				for (int i=velocY;i>0;i--) {
					g.drawRect(x, y-i, 32, 32);
				}
				tilt=0.5*Math.PI;
				g2d.rotate(tilt,ancho2,alto2);
				g.fillRect(x+32, y+12, 15, 10);
				g2d.rotate(-tilt,ancho2,alto2);
				
			} else
			if (velocY<0) { //arriba
				for (int i=velocY;i<0;i++) {
					g.drawRect(x, y-i, 32, 32);
				}
				tilt=1.5*Math.PI;
				g2d.rotate(tilt,ancho2,alto2);
				g.fillRect(x+32, y+12, 15, 10);
				g2d.rotate(-tilt,ancho2,alto2);
			}
		} else{
			if(velocX>0) {
				if(velocY>0) { //derecha abajo
					for (int i=velocX;i>0;i--) {
						g.drawRect(x-i, y-i, 32, 32);
					}
					tilt=0.25*Math.PI;
					g2d.rotate(tilt,ancho2,alto2);
					g.fillRect(x+16, y+16, 36, 10);
					g2d.rotate(-tilt,ancho2,alto2);
				} else { //derecha arriba
					for (int i=velocX;i>0;i--) {
						g.drawRect(x-i, y+i, 32, 32);
					}
					tilt=1.75*Math.PI;
					g2d.rotate(tilt,ancho2,alto2);
					g.fillRect(x+16, y+16, 36, 10);
					g2d.rotate(-tilt,ancho2,alto2);
				}
			} else
			if(velocX<0) { //izquierda abajo
				if(velocY>0) {
					for (int i=velocX;i<0;i++) {
						g.drawRect(x-i, y+i, 32, 32);
					}
					tilt=0.75*Math.PI;
					g2d.rotate(tilt,ancho2,alto2);
					g.fillRect(x+16, y+16, 36, 10);
					g2d.rotate(-tilt,ancho2,alto2);
				} else { //izquierda arriba
					for (int i=velocX;i<0;i++) {
						g.drawRect(x-i, y-i, 32, 32);
					}
					tilt=1.25*Math.PI;
					g2d.rotate(tilt,ancho2,alto2);
					g.fillRect(x+16, y+16, 36, 10);
					g2d.rotate(-tilt,ancho2,alto2);
				}
			}
		}
		//Color Jugadores
		if(id==ID.Player) {g.setColor(Color.white);}
		else {g.setColor(Color.red);};
		g.fillRect(x, y, 32, 32);
	}

	public void collision(){
		for (GameObject o: handler.getObjects()) {
			if (o.getId()==ID.BasicEnemy || o.getId()==ID.Bullet1 || o.getId()==ID.Bullet2) {
				if(o.getBounds().intersects(getBounds())) {
					HUD.vida1-=5;
				}
			}
		}
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}
}