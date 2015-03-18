package com.codergb.jsontilemapeditor.loader;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Handles all image loading for the program
 * @author Brendan Goodenough
 */

public class ImageLoader {
	
	public BufferedImage loadImage(String filename) {
		try {
			BufferedImage image = ImageIO.read(getClass().getResource(filename));
			return image;
		}
		catch (IOException e) {
			return new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		}
	}
	
}
