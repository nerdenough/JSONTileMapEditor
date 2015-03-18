package com.codergb.jsontilemapeditor.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import com.codergb.jsontilemapeditor.editor.Editor;
import com.codergb.jsontilemapeditor.json.JSONParser;
import com.codergb.jsontilemapeditor.main.Program;
import com.codergb.jsontilemapeditor.tile.Tile;

/**
 * Unit Tests for JSON TileMap Editor
 * @author Brendan Goodenough
 */

public class Tests {
	
	/**
	 * Attempts to add tiles to the map
	 */
	public @Test void addTiles() {
		Program program = new Program();
		Editor editor = program.getEditor();
		
		createTiles(editor);
		
		assertTrue(editor.getTiles().size() > 0);
	}
	
	/**
	 * Attempts to add tiles outside the rows/cols range
	 */
	public @Test void addTilesOutsideMap() {
		Program program = new Program();
		Editor editor = program.getEditor();
		
		editor.addTile(32, editor.getTileSize());
		editor.addTile(-1, 0);
		editor.addTile(0, -1);
		
		assertTrue(editor.getTiles().size() == 0);
	}
	
	/**
	 * Attempts to resize the UI
	 */
	public @Test void resizeUI() {
		Program program = new Program();
		Editor editor = program.getEditor();
		
		editor.setSize(32, 16, 8);
		
		assertTrue(editor.getRows() == 32);
		assertTrue(editor.getCols() == 16);
		assertTrue(editor.getTileSize() == 8);
	}
	
	/**
	 * Attempts to set an invalid number of rows
	 */
	public @Test void setInvalidRowSize() {
		Program program = new Program();
		Editor editor = program.getEditor();
		
		editor.setSize(32, 32, 32);
		editor.setSize(0, 32, 32);
		
		assertTrue(editor.getRows() == 32);
	}
	
	/**
	 * Attempts to set an invalid number of cols
	 */
	public @Test void setInvalidColSize() {
		Program program = new Program();
		Editor editor = program.getEditor();
		
		editor.setSize(32, 32, 32);
		editor.setSize(32, 0, 32);
		
		assertTrue(editor.getCols() == 32);
	}
	
	/**
	 * Attempts to set an invalid tilesize
	 */
	public @Test void setInvalidTileSize() {
		Program program = new Program();
		Editor editor = program.getEditor();
		
		editor.setSize(32, 32, 32);
		editor.setSize(32, 32, 0);
		
		assertTrue(editor.getTileSize() == 32);
	}
	
	/**
	 * Attempts to save a file
	 */
	public @Test void saveFile() {
		Program program = new Program();
		Editor editor = program.getEditor();
		JSONParser parser = new JSONParser();
		
		createTiles(editor);
		
		assertTrue(parser.saveJSON("C:\\Users\\Brendan\\Desktop\\testSave.json", editor.getTiles()));
	}
	
	/**
	 * Attemtps to save a file with an invalid name
	 */
	public @Test void saveInvalidFile() {
		Program program = new Program();
		Editor editor = program.getEditor();
		JSONParser parser = new JSONParser();
		
		createTiles(editor);
		
		assertFalse(parser.saveJSON("C:\\Users\\Brendan\\Desktop\\", editor.getTiles()));
	}
	
	/**
	 * Attempts to save a file in a new directory
	 */
	public @Test void saveFileNewDirectory() {
		Program program = new Program();
		Editor editor = program.getEditor();
		JSONParser parser = new JSONParser();
		
		createTiles(editor);
		
		assertTrue(parser.saveJSON("C:\\JSONTileMapEditor\\" + random() + "\\" + random() + ".json", editor.getTiles()));
	}
	
	/**
	 * Adds a range of tiles to the map
	 * @param tiles
	 */
	public void createTiles(Editor editor) {
		editor.addTile(0, 0);
		editor.addTile(1, 1);
		editor.addTile(2, 2);
		editor.addTile(3, 3);
		editor.addTile(4, 4);
		editor.addTile(5, 5);
	}
	
	/**
	 * Generates a random number
	 * @return random
	 */
	public int random() {
		Random rand = new Random();
		return rand.nextInt(Integer.MAX_VALUE);
	}

}
