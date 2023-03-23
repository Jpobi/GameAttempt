package com.attempt.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{

	private static final long serialVersionUID = -687869678449458051L;
	
	
	public Window(int ancho,int alto, String titulo,Game game) {
		JFrame marco=new JFrame(titulo);
		
		marco.setPreferredSize(new Dimension(ancho,alto));
		marco.setMaximumSize(new Dimension(ancho,alto));
		marco.setMinimumSize(new Dimension(ancho,alto));
		
		
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setResizable(false);
		marco.setLocationRelativeTo(null);
		marco.add(game);
		marco.setVisible(true);
		game.start();
	}
	
}
