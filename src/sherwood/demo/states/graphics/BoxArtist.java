package sherwood.demo.states.graphics;

import sherwood.demo.physics.Entity;
import sherwood.demo.entities.player.Player;
import sherwood.demo.structures.collisions.ArtStyle;

import java.awt.Graphics;

public class BoxArtist {

    private Graphics graphics;
    private ArtStyle style;

    public BoxArtist (Graphics graphics) {
        this(graphics, ArtStyle.FILL);
    }

    public BoxArtist (Graphics graphics, ArtStyle style) {
        this.graphics = graphics;
        this.style = style;
    }

    public void draw (Entity entity) {
        if (entity instanceof Player)
            graphics.setColor(((Player) entity).color());
        if (style == ArtStyle.FILL)
            graphics.fillRect(entity.bounds().position().xc(),
                    entity.bounds().position().yc(),
                    entity.bounds().size().xc(),
                    entity.bounds().size().yc());
        else
            graphics.drawRect(entity.bounds().position().xc(),
                    entity.bounds().position().yc(),
                    entity.bounds().size().xc(),
                    entity.bounds().size().yc());
    }
}
