package sherwood.demo.graphics;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpriteBox {

    private Map<String, Image> preloaded;

    public Image sprite (String name) {
        return preloaded.computeIfAbsent(name, SpriteBox::load);

    }

    private static Image load (String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (Exception e) {
            System.err.println("cannot load file " + name);
            throw new RuntimeException(e);
        }
    }

    public static SpriteBox instance () {
        if (box == null)
            box = new SpriteBox();
        return box;
    }

    private SpriteBox () {
        preloaded = new HashMap<>();
    }

    private static SpriteBox box;
}
