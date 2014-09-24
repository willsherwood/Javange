package sherwood.gameScreen.map;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * 
 */
public class FlipMap extends Mapping {

	private boolean flipX, flipY;
	
	public FlipMap(boolean flipX, boolean flipY) {
		this.flipX = flipX;
		this.flipY = flipY;
	}
	
	public FlipMap() {
		this(Math.random() > 0.5, Math.random() > 0.5);
	}
	
	@Override
	public Image map(Image img) {
		int height = img.getHeight(null), width = img.getWidth(null);
		BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int y=0; y<height; y++)
			for (int x=0; x<width; x++) {
				int prev = ((BufferedImage)img).getRGB(flipX ? width-x-1 : x, flipY ? height-y-1 : y);
				out.setRGB(x, y, prev);
			}
		return out;
	}
	

}
