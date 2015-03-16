package sherwood.gameScreen.map;

import java.awt.Image;

public abstract class Mapping {

    public static Image chain (Image img, Mapping... maps) {
        for (Mapping m : maps)
            img = m.map(img);
        return img;
    }

    public abstract Image map (Image img);
}
