package sherwood.demo.states.graphics;

import sherwood.demo.entities.Entity;

import java.awt.Graphics2D;

public class FillArtist implements Artist<Entity> {
    @Override
    public void draw (Entity entity, Graphics2D g) {
        g.fill(entity.bounds().rect());
    }
}
