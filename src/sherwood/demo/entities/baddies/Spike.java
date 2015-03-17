package sherwood.demo.entities.baddies;

import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Spike extends Baddie {

    private final Direction face;

    public Spike (BoundingBox bounds, Direction face) {
        super(bounds);
        this.face = face;
    }

    public Polygon poly () {
        int[] x, y;
        if (face.vertical()) {
            x = new int[]{bounds().position().xc(), bounds().position().xc() + bounds().size().xc() / 2, bounds().position().xc() + bounds().size().xc()};
            y = new int[]{bounds().position().yc() + bounds().size().yc(), bounds().position().yc(), bounds().position().yc() + bounds().size().yc()};
            if (face == Direction.DOWN) {
                for (int i = 0; i < 3; i++)
                    y[i] += (i == 1 ? 1 : -1) * bounds().size().yc();
            }
            return new Polygon(x, y, 3);
        }
        x = new int[]{bounds().position().xc(), bounds().position().xc() + bounds().size().xc(), bounds().position().xc()};
        y = new int[]{bounds().position().yc(), bounds().position().yc() + bounds().size().yc() / 2, bounds().position().yc() + bounds().size().yc()};
        if (face == Direction.LEFT) {
            for (int i = 0; i < 3; i++)
                x[i] += (i == 1 ? -1 : 1) * bounds().size().xc();
        }
        return new Polygon(x, y, 3);
    }

    @Override
    public void draw (Graphics2D g) {
        g.setColor(Color.RED);
        g.fill(poly());
    }
}
