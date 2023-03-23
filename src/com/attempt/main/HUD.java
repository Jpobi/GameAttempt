package com.attempt.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int vida1=100;
	public static int vida2=100;
	
	public static int points=0;
	
	public static void tick() {
		vida1=Game.clamp(vida1, 100, 0);
		vida2=Game.clamp(vida2, 100, 0);
	}
	
	public void render(Graphics g) {
		//vida1
		g.setColor(Color.gray);
		g.fillRect(15,15,220,32);
		g.setColor(new Color(200-vida1*2,0,vida1));
		g.fillRect(25,18,2*vida1,25);
		g.setColor(Color.white);
		g.drawRect(25,18,200,25);
		
		//vida2
		g.setColor(Color.gray);
		g.fillRect(400,15,220,32);
		g.setColor(new Color(200-vida2*2,vida2,0));
		g.fillRect(410,18,2*vida2,25);
		g.setColor(Color.white);
		g.drawRect(410,18,200,25);
		
		//points
		/*g.setColor(Color.green);
		g.setFont(new Font("Cracked", Font.BOLD, 25));
		g.drawChars(Integer.toString(points).toCharArray(), 0, Integer.toString(points).toCharArray().length, 552, 30);*/
	}
}
