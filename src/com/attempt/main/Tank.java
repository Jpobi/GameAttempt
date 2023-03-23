package com.attempt.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tank extends GameObject{
	
	protected double direction;
	protected double rotatSpeed;
	protected int sp33d;
	protected Handler handler;
	protected int shotTimer=0;
	
	public Tank(int x, int y, ID id,Handler handler,int ancho, int alto) {
		super(x, y, id,ancho, alto);
		this.handler=handler;
	}

	@Override
	public void tick() {
		direction+=getRotatSpeed();
		//if (sp33d>=0) {
		velocX=(int)(sp33d*(Math.cos(getDir())));
		velocY=(int)(sp33d*(Math.sin(getDir())));
		//}//System.out.println("VX="+velocX+" VY="+velocY);
		x+=velocX;
		y+=velocY;
		x=Game.clamp(x,Game.ancho-(ancho+2),0);
		y=Game.clamp(y,Game.alto-(alto*2),0);
		collision();
		if (shotTimer>0) {
			shotTimer-=1;
		}
	}

	@Override
	public void render(Graphics g) {
		BufferedImage img=null;
		BufferedImage img2=null;
				//Color rastros
			if(id==ID.Tank1) {
				g.setColor(Color.white);
				try {
					img= ImageIO.read(new File("assets/blueTank.png"));
					img2=ImageIO.read(new File("assets/cannon.png"));
				} catch(Exception e) {
					System.out.println("Error al encontrar blueTank.png.");
				}
			}
			else if(id==ID.Tank2){
				g.setColor(Color.blue);
				try {
					img= ImageIO.read(new File("assets/greenTank.png"));
					img2=ImageIO.read(new File("assets/cannon.png"));
				} catch(Exception e) {
					System.out.println("Error al encontrar greenTank.png.");
				}
			}
			else if(id==ID.TankEnemy){
				g.setColor(Color.white);
				try {
					img= ImageIO.read(new File("assets/redTank.png"));
					img2=ImageIO.read(new File("assets/cannon.png"));
				} catch(Exception e) {
					System.out.println("Error al encontrar redTank.png.");
				}
			};
			Graphics2D g2d=(Graphics2D)g;
			g2d.setColor(g.getColor());
				
			double centroX=x+ancho/2; //X del centro del tanque
			double centroY=y+alto/2; //Y del centro del tanque
				
			//  -> 0
			//  \/ pi/2
			//  <- pi
			//  ^  1.5*pi
			if(velocX==0 || velocY==0) {
				if (velocX==0 && velocY==0) {
					g2d.rotate(direction,centroX,centroY); //rotate (�ngulo, valor X del eje de rot,valor y de eje de rot)
					g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
					g.drawImage(img, x, y, ancho, alto, null);
					g2d.rotate(-direction,centroX,centroY);
				}
				if(velocX>0) { //Derecha
					direction=0;
					for (int i=velocX;i>0;i--) {
						g.drawRect(x-i, y, ancho, alto);
					}
					g2d.rotate(direction,centroX,centroY);
					g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
					g.drawImage(img, x, y, ancho, alto, null);
					g2d.rotate(-direction,centroX,centroY);
				} else
				if(velocX<0) { //Izquierda
					direction=Math.PI;
					for (int i=velocX;i<0;i++) {
						g.drawRect(x-i, y, ancho, alto);
					}
					g2d.rotate(direction,centroX,centroY);
					g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
					g.drawImage(img, x, y, ancho, alto, null);
					g2d.rotate(-direction,centroX,centroY);
				}
				if (velocY>0) { //abajo
					direction=0.5*Math.PI;
					for (int i=velocY;i>0;i--) {
						g.drawRect(x, y-i, ancho, alto);
					}
					g2d.rotate(direction,centroX,centroY);
					g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
					g.drawImage(img, x, y, ancho, alto, null);
					g2d.rotate(-direction,centroX,centroY);
					
				} else
				if (velocY<0) { //arriba
					direction=1.5*Math.PI;
					for (int i=velocY;i<0;i++) {
						g.drawRect(x, y-i, ancho, alto);
					}
					g2d.rotate(direction,centroX,centroY);
					g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
					g.drawImage(img, x, y, ancho, alto, null);
					g2d.rotate(-direction,centroX,centroY);
				}
				} else{
				if(velocX>0) {
					if(velocY>0) { //derecha abajo
						direction=0.25*Math.PI;
						g2d.rotate(direction,centroX,centroY);
						for (int i=velocX;i>0;i--) {
							g.drawRect(x-i, y, ancho, alto);
						}
						g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
						g.drawImage(img, x, y, ancho, alto, null);
						g2d.rotate(-direction,centroX,centroY);
					} else { //derecha arriba
						direction=1.75*Math.PI;
						g2d.rotate(direction,centroX,centroY);
						for (int i=velocX;i>0;i--) {
							g.drawRect(x-i, y, ancho, alto);
						}
						g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
						g.drawImage(img, x, y, ancho, alto, null);
						g2d.rotate(-direction,centroX,centroY);
					}
				} else
				if(velocX<0) { //izquierda abajo
					if(velocY>0) {
						direction=0.75*Math.PI;
						g2d.rotate(direction,centroX,centroY);
						for (int i=velocX;i<0;i++) {
							g.drawRect(x+i, y, ancho, alto);
						}
						g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
						g.drawImage(img, x, y, ancho, alto, null);
						g2d.rotate(-direction,centroX,centroY);
					} else { //izquierda arriba
						direction=1.25*Math.PI;
						g2d.rotate(direction,centroX,centroY);
						for (int i=velocX;i<0;i++) {
							g.drawRect(x+i, y, ancho, alto);
						}
						g.drawImage(img2, x+ancho/2, y+alto/4, (ancho*5)/6, (alto*2)/3, null);
						g.drawImage(img, x, y, ancho, alto, null);
						g2d.rotate(-direction,centroX,centroY);
					}
				}
			}
			g.setColor(Color.white);
			g.fillRect(x, y-5, ancho*shotTimer/160, 5);
			//Color Jugadores
			//if(id==ID.Tank1) {g.setColor(Color.blue);}
			//else if(id==ID.Tank2){g.setColor(Color.green);}
			//else if(id==ID.TankEnemy){g.setColor(Color.red);};
			//g.fillRect(x, y, ancho, alto);
	}
	
	public void collision(){
		for (GameObject o: handler.getObjects()) {
			if ((o.getId()==ID.BasicEnemy || o.getId()==ID.Bullet2) && id==ID.Tank1) {
				if(o.getBounds().intersects(getBounds())) {
					HUD.vida1-=5;
				}
			} else if ((o.getId()==ID.BasicEnemy || o.getId()==ID.Bullet1) && id==ID.Tank2) {
				if(o.getBounds().intersects(getBounds())) {
					HUD.vida2-=5;
				}
			} else if (( o.getId()==ID.Bullet1 || o.getId()==ID.Bullet2) && id==ID.TankEnemy) {
				if(o.getBounds().intersects(getBounds())) {
					((TankEnemy)this).setVida(((TankEnemy)this).getVida()-5);
				}
			} else if (o.getId()==ID.Player || o.getId()==ID.Tank1 || o.getId()==ID.Player2 || o.getId()==ID.Tank2|| o.getId()==ID.TankEnemy || o.getId()==ID.Wall) {
				if(o.getBounds().intersects(getBounds()) && id!=o.getId()) {
					x=x-velocX;
					y=y-velocY;
					sp33d=0;
				}
			}
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,ancho,alto);
	}

	public double getDir() {
		return direction;
	}

	public void rotateDir(double direction) {
		this.direction = direction;
	}

	public int getSp33d() {
		return sp33d;
	}

	public void setSp33d(int sp33d) {
		this.sp33d = sp33d;
	}

	public double getRotatSpeed() {
		return rotatSpeed;
	}

	public void setRotatSpeed(double rotatSpeed) {
		this.rotatSpeed = rotatSpeed;
	}

	public int getShotTimer() {
		return shotTimer;
	}

	public void setShotTimer(int shotTimer) {
		this.shotTimer = shotTimer;
	}
	
}