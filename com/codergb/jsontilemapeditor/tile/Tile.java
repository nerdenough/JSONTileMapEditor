package com.codergb.jsontilemapeditor.tile;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Tile {
	
	private int row, col;
	private Color color;
	private BufferedImage image;
	
	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
		color = new Color(0x000000);
		image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	}
	
	public int getRow() { return row; }
	public int getCol() { return col; }
	public Color getColor() { return color; }
	public BufferedImage getImage() { return image; }

}
