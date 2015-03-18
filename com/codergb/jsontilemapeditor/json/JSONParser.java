package com.codergb.jsontilemapeditor.json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.codergb.jsontilemapeditor.tile.Tile;

public class JSONParser {
	
	public JSONParser() {
		
	}
	
	public ArrayList<Tile> loadJSON(String filename) {
		return new ArrayList<>();
	}
	
	/**
	 * Saves current tile data to a JSON file
	 * @param filename name and directory to save file
	 * @param tiles collection of tiles
	 * @return saved successfully
	 */
	public boolean saveJSON(String filename, ArrayList<Tile> tiles) {
		// Create JSON
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		
		// Add tiles
		for (Tile tile : tiles) {
			JSONObject tileObj = new JSONObject();
			tileObj.put("Row", tile.getRow());
			tileObj.put("Col", tile.getCol());
			
			array.put(tileObj);
		}
		
		obj.put("Tiles", array);
		
		// Write to file
		try {
			File file = new File(filename);
			File dir = new File(filename.substring(0, filename.lastIndexOf("\\")));
			
			if (!dir.exists()) {
				dir.mkdir();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter fw = new FileWriter(file.getAbsolutePath());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(obj.toString());
			bw.close();
			
			return true;
		}
		catch (IOException e) {
			return false;
		}
	}

}
