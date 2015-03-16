package sherwood.gameScreen.map;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 */
public class StaticMap extends Mapping {

    private int maxStatic;

    public StaticMap (int maxStatic) {
        this.maxStatic = maxStatic;
    }

    @Override
    public Image map (Image img) {
        int height = img.getHeight(null), width = img.getWidth(null);
        BufferedImage out = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                Color prev = new Color(((BufferedImage) img).getRGB(x, y));
                out.setRGB(
                        x,
                        y,
                        new Color(
                                getSumMod(prev.getRed(),
                                        (int) (Math.random() * maxStatic), 256),
                                getSumMod(prev.getGreen(),
                                        (int) (Math.random() * maxStatic), 256),
                                getSumMod(prev.getBlue(),
                                        (int) (Math.random() * maxStatic), 256))
                                .getRGB());
            }
        return out;
    }

    private int getSumMod (int a, int b, int max) {
        if (a + b >= max)
            return max - 1;
        return a + b > 0 ? a + b : 0;
    }

}
