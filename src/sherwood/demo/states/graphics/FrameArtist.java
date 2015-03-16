package sherwood.demo.states.graphics;

import sherwood.demo.entities.Entity;

import java.awt.Graphics2D;

public class FrameArtist implements Artist<Entity> {
    @Override
    public void draw (Entity entity, Graphics2D g) {
        g.draw(entity.bounds().rect());
    }
}
