package com.attempt.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;

public class TankEnemy extends Tank{
	
	private Rectangle rect;
	private int turnos=0;
	private double wantedDir=0;
	
	private int vida=100;
	
	public TankEnemy(int x, int y, ID id, Handler handler,int ancho, int alto) {
		super(x, y, id, handler,ancho,alto);
	}
	
	@Override
	public void tick() {
		direction+=getRotatSpeed();
		direccion.rotateBy(getRotatSpeed());
		if(turnos==0) {
			if (calcSp33dAI()){
				turnos=20;
			} else {
				//aim();
				//System.out.println("Direction (after): "+direction);
				this.rotatSpeed=0;
				velocidadNeta=0;
			}
		} else {
			turnos--;
		}
		// velocX=(int)(velocidadNeta*(Math.cos(getDir())));
		// velocY=(int)(velocidadNeta*(Math.sin(getDir())));
		velocX=(int)(velocidadNeta*direccion.x);
		velocY=(int)(velocidadNeta*direccion.y);
		//System.out.println("VX="+velocX+" VY="+velocY);
		x+=velocX;
		y+=velocY;
		x=Game.clamp(x,Game.ancho-(ancho+2),0);
		y=Game.clamp(y,Game.alto-alto*2,0);
		vida=Game.clamp(vida,100,0);
		if (vida<=0) {
			handler.removeObject(this);
		}
		collision();
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(200-vida*2,vida*2,0));
		g.fillRect(x, y-15, ancho*vida/100, 10);		
	}
	
	public void aim() {
		int aimX=this.velocX,aimY=this.velocY;
		double dir=0;
		for(GameObject o:handler.objects) {
			if(o.getId()==ID.Tank1) { //|| o.getId()==ID.Tank2) {
				aimX=o.getX()-this.velocX;
				aimY=o.getY()-this.velocY;
				dir=(Math.atan2(aimX,aimY))-(Math.atan2(aimX,aimY))%(0.15*Math.PI);
			}
		}
		if((int)(this.direction)!=(int)dir) {
			System.out.println("aiming: "+(float)this.rotatSpeed);
			if ((this.direction-dir)<0) {
				this.rotatSpeed=0.15*Math.PI;
			} else {
				this.rotatSpeed=-0.15*Math.PI;
			}
		} else {
			handler.objects.add(new BasicEnemy(this.x+8+aimX,this.y+8+aimY,aimX,aimY,ID.BasicEnemy,handler,16,16));
		}
		
	}
	
	public Vector2D getPerpendicularDirection(GameObject o) {
		// double x = o.getVelocY();
		// double y = -o.getVelocX();
		// Vector2D result=new Vector2D(x, y);
		Vector2D result=o.direccion.getPerp();

		if (this.x+result.x*5<0 || this.x+result.x*5>Game.ancho || this.y+result.y*5<0 || this.y+result.y*5>Game.alto ){
			result.rotateBy(90);
		}

		return result;
	}

	private double calcularPerpendicularA(GameObject o){

		if(o.getVelocX()==0) {
			if(o.getVelocY()>0) {
				wantedDir=Math.PI;
			} else {
				wantedDir=0;
			}
		} else if(o.getVelocY()==0) {
			if(o.getVelocX()>0) {
				wantedDir=Math.PI/2;
			} else {
				wantedDir=1.5*Math.PI;
			}
		} else{
			wantedDir=(Math.atan2(o.getVelocX(),(-o.getVelocY())))-(Math.atan2(o.getVelocX(),(-o.getVelocY())))%(0.15*Math.PI);
		}
		//this.direction=	Double.parseDouble((String.format("%.1f", this.direction)));
		//wantedDir= Double.parseDouble((String.format("%.01f", wantedDir)));
		wantedDir=Math.toRadians(wantedDir);

		return wantedDir;
	}

	public void rotateAway(){

		//!a.equals(b) = !(a.equals(b))  >>=  a!=b (almenos para strings)
		if(!String.format("%.01f", this.direction).equals(String.format("%.01f", wantedDir))) {
			if ((this.direction-wantedDir)<0) {
				this.rotatSpeed=0.05*Math.PI;
			} else {
				this.rotatSpeed=-0.05*Math.PI;
			}
			System.out.println("Direction (before): "+String.format("%.02f", direction));
			System.out.println("WantedDir: "+String.format("%.02f", wantedDir));
			System.out.println("rotating: "+(float)this.rotatSpeed);
		} else {
			this.rotatSpeed=0;
		}
		//if(this.rotatSpeed==0) {
			System.out.println("moving");
			this.velocidadNeta=5;
		//}
	}

	public boolean calcSp33dAI() {
		
		//Si toca el borde que apunte hacia otro lado
		/*
		if(x==Game.ancho-34) {
			wantedDir=Math.PI;
			if(y==Game.alto-62) {
				wantedDir=1.25*Math.PI;
			}
		} else if(y==Game.alto-62) {
			wantedDir=1.5*Math.PI;
			if(x==0) {
				wantedDir=1.75*Math.PI;
			}
		} else if(x==0) {
			wantedDir=0;
			if(y==0) {
				wantedDir=0.25*Math.PI;
			}
		} else if(y==0) {
			wantedDir=0.5*Math.PI;
			if(x==0) {
				wantedDir=0.75*Math.PI;
			}
		} else {
			velocidadNeta=0;
		}
		
		if((this.direction)!=wantedDir) {
			System.out.println("rotating: "+(float)this.rotatSpeed);
			if ((this.direction-wantedDir)<0) {
				this.rotatSpeed=0.15*Math.PI;
			} else {
				this.rotatSpeed=-0.15*Math.PI;
			}
			this.velocidadNeta=5;
			System.out.println("DIRECTION: "+this.direction);
			System.out.println("Wanted Dir: "+wantedDir);
			return true;
		}*/
		
		for(GameObject o:handler.objects) { //entre todos los objetos
			// Rectangle laser=new Rectangle(this.x,this.y);
			if(o.getId()==ID.Bullet1 || o.getId()==ID.Bullet2){ //si es bala
				for (int n=0;n<30;n++) {
					Rectangle rect=new Rectangle(o.getX()+o.getVelocX()*n,o.getY()+o.getVelocY()*n,16,16);
					if ( rect.intersects(this.getBounds()) ){ //y me chocará
						
						// wantedDir=calcularPerpendicularA(o);
						wantedDir=getPerpendicularDirection(o).getAngle();
						
						rotateAway();

						return true; //detectó una bala acercándose
					} else {
						//this.rotatSpeed=0;
					}
				}
			}
		}
		return false;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
}
