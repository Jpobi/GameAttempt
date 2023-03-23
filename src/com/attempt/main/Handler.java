package com.attempt.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> objects=new LinkedList<>();
	
	
	public void tick() {
		for(int i=0;i<objects.size();i++) {
			GameObject aux= objects.get(i);
			
			aux.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0;i<objects.size();i++) {
			GameObject aux= objects.get(i);
			
			aux.render(g);
		}
	}
	
	public void addObject(GameObject go) {
		this.objects.add(go);
	}
	
	public void removeObject(GameObject go) {
		this.objects.remove(go);
	}

	public LinkedList<GameObject> getObjects() {
		return objects;
	}
	
}
