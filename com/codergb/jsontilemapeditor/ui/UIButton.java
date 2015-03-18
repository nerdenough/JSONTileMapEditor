package com.codergb.jsontilemapeditor.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * Styled button to be used in the UI
 */
@SuppressWarnings("serial")
public class UIButton extends JButton implements MouseListener {
	
	private String text;
	private boolean hovered, clicked;
	
	public UIButton(String text) {
		this.text = text;
		this.setText(text);
		this.setBorderPainted(false);
		this.setPreferredSize(new Dimension(100, 40));
		
		// Add a mouse listener to the button
		addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw background and borders
		if (clicked)
			g.setColor(new Color(250, 250, 255));
		else
			g.setColor(new Color(252, 252, 252));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (hovered)
			g.setColor(new Color(180, 180, 255));
		else
			g.setColor(new Color(220, 220, 220));
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		
		// Draw text
		if (hovered) 
			g.setColor(new Color(140, 140, 220));
		else
			g.setColor(new Color(180, 180, 180));
		g.drawString(text, getWidth()/2 - g.getFontMetrics().stringWidth(text)/2,  getHeight()/2 + g.getFontMetrics().getDescent() + 1);
	}

	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) {
		hovered = true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		hovered = false;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		clicked = true;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicked = false;
		repaint();
	}
	
}