package com.attempt.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class BasicEnemy extends GameObject{
	
	private Random r;
	private Handler handler;
	
	private Color color;
	private int bounced=0;
	
	public BasicEnemy(int x, int y, ID id,Handler handler,int ancho, int alto) {
		super(x, y, id,ancho, alto);
		
		r=new Random();
		
		velocX=Game.clamp(r.nextInt(4),4,1);
		velocY=Game.clamp(r.nextInt(4),4,1);
		
		//ancho=Game.clamp(r.nextInt(20),20,16);
		//alto=Game.clamp(r.nextInt(20),20,16);
		
		this.handler=handler;
	}
	
	public BasicEnemy(int x, int y,int velocX,int velocY, ID id,Handler handler,int ancho, int alto) {
		super(x, y, id,ancho, alto);
		
		r=new Random();
		
		this.velocX=velocX;
		this.velocY=velocY;
		
		//System.out.println("VX= "+this.velocX+", VY= "+this.velocY);
		
		//ancho=Game.clamp(r.nextInt(20),20,16);
		//alto=Game.clamp(r.nextInt(20),20,16);
		
		this.handler=handler;
	}
	

	@Override
	public void tick() {
		x+=velocX;
		y+=velocY;
		
		if(x<0 || x>Game.ancho-(ancho+2)) {
			velocX*=-1;
			bounced++;
		}
		if(y<0 || y>Game.alto-(alto*3)) {
			velocY*=-1;
			bounced++;
		}
		if (bounced>2) {
			/*if(this.id==ID.Bullet1) {
				KeyInput.setB1(KeyInput.getB1()-1);
			} else if(this.id==ID.Bullet2) {
				KeyInput.setB2(KeyInput.getB2()-1);
			}*/
			handler.removeObject(this);
		}
		handler.addObject(new Trail(x, y, ancho, alto, 0.08f, ID.Trail, color, handler));
		collision();
	}

	@Override
	public void render(Graphics g) {
		BufferedImage img=null;
		try {
			if(id==ID.Bullet1) {
				this.color=Color.blue;
				g.setColor(Color.blue);
				img= ImageIO.read(new File("assets/blueBullet.png"));}
			else if(id==ID.Bullet2) {
				this.color=Color.green;
				g.setColor(Color.green);
				img= ImageIO.read(new File("assets/greenBullet.png"));
			}else if(id==ID.BasicEnemy) {
				this.color=Color.red;
				g.setColor(Color.red);
				img= ImageIO.read(new File("assets/redBullet.png"));
			};
		} catch(Exception e) {
			System.out.println("Error al encontrar Exhaust_Fire.png.");
		}
		//g.fillRect(x,y,ancho,alto);
		g.drawImage(img, x, y, ancho*2, alto*2, null);
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,ancho,alto);
	}
	public void collision(){
		GameObject o;
		for (int i=0;i<handler.objects.size();i++) {
			o=handler.objects.get(i);
			if ((o.getId()==ID.Player || o.getId()==ID.Player2 || o.getId()==ID.Tank1 || o.getId()==ID.TankEnemy) && id==ID.Bullet2) {
				if(o.getBounds().intersects(getBounds())) {
					o.tick();
					//KeyInput.setB2(KeyInput.getB2()-1);
					handler.objects.remove(this);
				}
			} else if ((o.getId()==ID.Player || o.getId()==ID.Player2 || o.getId()==ID.Tank2 || o.getId()==ID.TankEnemy) && id==ID.Bullet1) {
				if(o.getBounds().intersects(getBounds())) {
					o.tick();
					//KeyInput.setB2(KeyInput.getB1()-1);
					handler.objects.remove(this);
				}
			} else if ((o.getId()==ID.Player || o.getId()==ID.Player2|| o.getId()==ID.Tank1 || o.getId()==ID.Tank2) && id==ID.BasicEnemy) {
				if(o.getBounds().intersects(getBounds())) {
					o.tick();
					this.velocX*=-1;
					this.velocY*=-1;
				}
			} else if(o.getId()==ID.Wall) {
				Wall h=(Wall)o;
				if(h.collidesWith(this)==1) {
					this.velocX=this.velocX*-1;
					bounced++;
				} else if(h.collidesWith(this)==2) {
					this.velocY=this.velocY*-1;
					bounced++;
				}  else if(h.collidesWith(this)==3) {
					this.velocX=this.velocX*-1;
					this.velocY=this.velocY*-1;
					bounced++;
				}
			}
		}
	}
}

