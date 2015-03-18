package com.codergb.jsontilemapeditor.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UITextField extends JTextField {
	
	public UITextField(String text) {
		this.setText(text);
		this.setForeground(new Color(120, 120, 120));
		this.setBorder(new EmptyBorder(0, 10, 0, 10));
		this.setPreferredSize(new Dimension(40, 40));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Border
		g.setColor(new Color(220, 220, 220));
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
	}

}
