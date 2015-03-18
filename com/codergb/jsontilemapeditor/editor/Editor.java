package com.codergb.jsontilemapeditor.editor;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.codergb.jsontilemapeditor.tile.Tile;

public class Editor extends Canvas implements MouseListener {

	// Generated
	private static final long serialVersionUID = 2375339674903677988L;
	
	private int width, height, xOrigin, yOrigin, rows, cols, tileSize;
	private boolean showGrid;
	
	private ArrayList<Tile> tiles;
	
	/**
	 * Constructs a new Editor
	 * @param rows of tiles
	 * @param cols of tiles
	 * @param tileSize of tiles
	 */
	public Editor(int rows, int cols, int tileSize) {
		this.width = cols * tileSize;
		this.height = rows * tileSize;
		this.rows = rows;
		this.cols = cols;
		this.tileSize = tileSize;
		showGrid = true;
		
		tiles = new ArrayList<>();
		
		addMouseListener(this);
	}
	
	public ArrayList<Tile> getTiles() { return tiles; }
	public int getRows() { return rows; }
	public int getCols() { return cols; }
	public int getTileSize() { return tileSize; }
	
	public void setSize(int rows, int cols, int tileSize) {
		if (rows > 0) this.rows = rows;
		if (cols > 0) this.cols = cols;
		if (tileSize > 0) this.tileSize = tileSize;
		this.width = this.cols * tileSize;
		this.height = this.rows * tileSize;
		repaint();
	}
	
	/**
	 * Paints on the canvas
	 */
	public void paint(Graphics g) {
		// Calculate origins
		xOrigin = getWidth()/2 - width/2;
		yOrigin = getHeight()/2 - height/2;
		
		// Draw background
		g.setColor(new Color(250, 250, 250));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(xOrigin, yOrigin, width, height);
		
		// Draw tiles
		drawTiles(g);
		
		// Draw grid
		if (showGrid) drawGrid(g);
	}
	
	/**
	 * Draw a grid on the canvas
	 * @param g
	 */
	private void drawGrid(Graphics g) {
		// Draw grid
		g.setColor(new Color(220, 220, 220));
		for (int row = 0; row <= rows; row++) {
			g.drawLine(xOrigin, yOrigin + (row * tileSize), xOrigin + width - 1, yOrigin + (row * tileSize));
			
			for (int col = 0; col <= cols; col++) {
				g.drawLine(xOrigin + (col * tileSize), yOrigin, xOrigin + (col * tileSize), yOrigin + height - 1);
			}
		}
	}
	
	private void drawTiles(Graphics g) {
		g.setColor(Color.BLACK);
		for (Tile tile : tiles) {
			g.fillRect(xOrigin + tile.getCol() * tileSize, yOrigin + tile.getRow() * tileSize, tileSize, tileSize);
		}
	}
	
	public void addTile(int row, int col) {
		if (row < 0 || row >= rows || col < 0 || col >= cols)
			return;
		
		tiles.add(new Tile(row, col));
	}
	
	public void removeTile(int row, int col) {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) {
		int button = e.getButton();
		
		if (button == MouseEvent.BUTTON1) {
			Point p = e.getPoint();
			
			// Outside the tile window
			if (p.x - xOrigin < 0 || p.y - yOrigin < 0 || p.x - xOrigin > width || p.y - yOrigin > height) {
				return;
			}
			
			// Calculate row and column
			int row = (int) Math.floor((p.y - yOrigin) / tileSize);
			int col = (int) Math.floor((p.x - xOrigin) / tileSize);
			
			// Check to see whether there is already a tile
			for (int i = tiles.size()-1; i >= 0; i--) {
				Tile tile = tiles.get(i);
				if (row == tile.getRow() && col == tile.getCol()) {
					tiles.remove(i);
				}
			}
			
			// Add the tile and repaint
			addTile(row, col);
			repaint();
		}
		
		if (button == MouseEvent.BUTTON3) {
			Point p = e.getPoint();
			
			// Outside the tile window
			if (p.x - xOrigin < 0 || p.y - yOrigin < 0 || p.x - xOrigin > width || p.y - yOrigin > height) {
				return;
			}
			
			// Calculate row and column
			int row = (int) Math.floor((p.y - yOrigin) / tileSize);
			int col = (int) Math.floor((p.x - xOrigin) / tileSize);
			
			// Check whether a tile exists
			for (int i = tiles.size()-1; i >= 0; i--) {
				Tile tile = tiles.get(i);
				if (row == tile.getRow() && col == tile.getCol()) {
					tiles.remove(i);
				}
			}
			
			// Add the tile and repaint
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) { }

}
