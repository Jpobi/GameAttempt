package com.attempt.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter{
	
	private Handler handler=new Handler();
	private int velocidadNeta=5;
	
	private boolean uP = false;
	private boolean dP = false;
	private boolean lP = false;
	private boolean rP = false;	
	
	//private static int b1=0,b2=0;
	
	public KeyInput(Handler handler) {
		this.handler=handler;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		//System.out.println(key);
		
		for(int i=0;i<handler.objects.size();i++) {
			GameObject aux=handler.objects.get(i);
			if(aux.id==ID.Player) {
				if(key==87) {
					uP = true;
					handler.getObjects().get(i).setVelocY(-velocidadNeta);
				} else if(key==65) {
					lP = true;
					handler.getObjects().get(i).setVelocX(-velocidadNeta);
				} else if(key==68) {
					rP = true;
					handler.getObjects().get(i).setVelocX(velocidadNeta);
				} else if(key==83) {
					dP = true;
					handler.getObjects().get(i).setVelocY(velocidadNeta);
				}
			} else if(aux.id==ID.Tank1) {
				if(key==77) {
					// int velocXB=(int)(4*(Math.cos(((Tank)aux).getDir())));
					// int velocYB=(int)(4*(Math.sin(((Tank)aux).getDir())));
					int velocXB=(int)(4*aux.direccion.x);
					int velocYB=(int)(4*aux.direccion.y);
					
					
					//TIMER
					if (((Tank)aux).getShotTimer()<=0) {
						handler.objects.add(new BasicEnemy(aux.x+4+velocXB*2,aux.y+2+velocYB*2,velocXB,velocYB,ID.Bullet1,handler,aux.getAncho()/2,aux.getAlto()/2));
						((Tank)aux).setShotTimer(160);
					} else {
						System.out.println(((Tank)aux).id+" no puede disparar durante "+((Tank)aux).getShotTimer()+" ticks.");
					}
				}else if(key==38) {
					uP = true;
					if(rP){
                    	((Tank)aux).setRotatSpeed(0.02*Math.PI);
                    }else if(lP){
                    	((Tank)aux).setRotatSpeed(-0.02*Math.PI);
                    }
					((Tank)aux).setSp33d(velocidadNeta);
				} else if(key==37) {
					lP = true;
					((Tank)aux).setRotatSpeed(-0.02*Math.PI);
				} else if(key==39) {
					rP = true;
					((Tank)aux).setRotatSpeed(0.02*Math.PI);
				} else if(key==40) {
					dP = true;
					if(rP){
                    	((Tank)aux).setRotatSpeed(0.02*Math.PI);
                    }else if(lP){
                    	((Tank)aux).setRotatSpeed(-0.02*Math.PI);
                    }
					/* REVISAR!!
					((Tank)aux).setSp33d(-velocidadNeta);
					((Tank)aux).setVelocX((int)(velocidadNeta*(Math.cos(((Tank)aux).getDir()+Math.PI))));
					((Tank)aux).setVelocY((int)(velocidadNeta*(Math.sin(((Tank)aux).getDir()+Math.PI))));
					*/
				}
			} else if(aux.id==ID.Tank2) {
				if(key==70) {
					// int velocXB=(int)(4*(Math.cos(((Tank)aux).getDir())));
					// int velocYB=(int)(4*(Math.sin(((Tank)aux).getDir())));
					int velocXB=(int)(4*aux.direccion.x);
					int velocYB=(int)(4*aux.direccion.y);

					//TIMER
					if (((Tank)aux).getShotTimer()<=0) {
						handler.objects.add(new BasicEnemy(aux.x+4+velocXB*2,aux.y+2+velocYB*2,velocXB,velocYB,ID.Bullet2,handler,16,16));
						((Tank)aux).setShotTimer(160);
					} else {
						System.out.println(((Tank)aux).id+" no puede disparar durante "+((Tank)aux).getShotTimer()+" ticks.");
					}
				}else if(key==87) {
					uP = true;
					if(rP && !lP){
                    	((Tank)aux).setRotatSpeed(0.02*Math.PI);
                    }else if(lP && !rP){
                    	((Tank)aux).setRotatSpeed(-0.02*Math.PI);
                    }
					((Tank)aux).setSp33d(velocidadNeta);
				} else if(key==65) {
					lP = true;
					((Tank)aux).setRotatSpeed(-0.02*Math.PI);
					rP=false;
				} else if(key==68) {
					rP = true;
					((Tank)aux).setRotatSpeed(0.02*Math.PI);
					lP=false;
				} else if(key==83) {
					dP = true;
					if(rP){
                    	((Tank)aux).setRotatSpeed(0.02*Math.PI);
                    }else if(lP){
                    	((Tank)aux).setRotatSpeed(-0.02*Math.PI);
                    }
					/* REVISAR!!
					((Tank)aux).setSp33d(-velocidadNeta);
					((Tank)aux).setVelocX((int)(velocidadNeta*(Math.cos(((Tank)aux).getDir()+Math.PI))));
					((Tank)aux).setVelocY((int)(velocidadNeta*(Math.sin(((Tank)aux).getDir()+Math.PI))));
					*/
				}
			}
		}
		if (key==KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		//System.out.println(key);
		for(int i=0;i<handler.objects.size();i++) {
			GameObject aux=handler.objects.get(i);
			if(aux.id==ID.Player) {
				if(key==87) {
					uP = false;
                    if(dP){
                    	handler.getObjects().get(i).setVelocY(velocidadNeta);
                    }else {
                    	handler.getObjects().get(i).setVelocY(0);
                    }
				} else if(key==65) {
					lP = false;
                    if(rP){
                    	handler.getObjects().get(i).setVelocX(velocidadNeta);
                    }else {
                    	handler.getObjects().get(i).setVelocX(0);
                    }
				} else if(key==68) {
					rP = false;
                    if(lP){
                    	handler.getObjects().get(i).setVelocX(-velocidadNeta);
                    }else {
                    	handler.getObjects().get(i).setVelocX(0);
                    }
				} else if(key==83) {
					dP = false;
                    if(uP){
                    	handler.getObjects().get(i).setVelocY(-velocidadNeta);
                    }else {
                    	handler.getObjects().get(i).setVelocY(0);
                    }
				}
			} else if(aux.id==ID.Tank1) {
				if(key==38) {
					uP = false;
                    /*if(dP){
                    	((Tank)aux).setSp33d(velocidadNeta);
                    }else {*/
                    	((Tank)aux).setSp33d(0);
                    //}
				} else if(key==37) {
					lP = false;
					if(rP){
                    	((Tank)aux).setRotatSpeed(0.02*Math.PI);
                    }else {
                    	((Tank)aux).setRotatSpeed(0);
                    }
				} else if(key==39) {
					rP = false;
					if(lP){
                    	((Tank)aux).setRotatSpeed(-0.02*Math.PI);
                    }else {
                    	((Tank)aux).setRotatSpeed(0);
                    }
				} else if(key==40) {
					dP = false;
                    if(uP){
                    	((Tank)aux).setSp33d(-velocidadNeta);
                    }else {
                    	((Tank)aux).setSp33d(0);
                    }
				}
			}  else if(aux.id==ID.Tank2) {
				if(key==87) {
					uP = false;
                    /*if(dP){
                    	((Tank)aux).setSp33d(velocidadNeta);
                    }else {*/
                    	((Tank)aux).setSp33d(0);
                    //}
				} else if(key==65) {
					lP = false;
					if(rP){
                    	((Tank)aux).setRotatSpeed(0.02*Math.PI);
                    }else {
                    	((Tank)aux).setRotatSpeed(0);
                    }
				} else if(key==68) {
					rP = false;
					if(lP){
                    	((Tank)aux).setRotatSpeed(-0.02*Math.PI);
                    }else {
                    	((Tank)aux).setRotatSpeed(0);
                    }
				} else if(key==83) {
					dP = false;
                    if(uP){
                    	((Tank)aux).setSp33d(-velocidadNeta);
                    }else {
                    	((Tank)aux).setSp33d(0);
                    }
				}
			}
		}
	}

	/*public static int getB1() {
		return b1;
	}

	public static void setB1(int b1) {
		KeyInput.b1 = b1;
	}

	public static int getB2() {
		return b2;
	}

	public static void setB2(int b2) {
		KeyInput.b2 = b2;
	}*/
}
