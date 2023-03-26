package com.attempt.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x,y;
	protected int velocX,velocY;
	protected ID id;
	protected int ancho,alto;
	protected Vector2D direccion;
	// protected Vector2D direccionApuntado;
	protected double direction;
	protected double rotatSpeed;
	protected int velocidadNeta;
	protected Handler handler;
	
	
	protected GameObject(int x,int y,ID id,int ancho, int alto) {
		this.x=x;
		this.y=y;
		this.id=id;
		this.ancho=ancho;
		this.alto=alto;
		this.direccion=new Vector2D(0,1);
		this.direccion.normalize();
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	//Getters y Setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public int getVelocX() {
		return velocX;
	}
	public void setVelocX(int velocX) {
		this.velocX = velocX;
	}
	public int getVelocY() {
		return velocY;
	}
	public void setVelocY(int velocY) {
		this.velocY = velocY;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	//End Getters y setters
	
}
