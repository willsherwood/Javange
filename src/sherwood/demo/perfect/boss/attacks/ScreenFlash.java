package sherwood.demo.perfect.boss.attacks;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Effect;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.gameScreen.GameScreen;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class ScreenFlash implements Effect, Drawable {

    public ScreenFlash() {

    }

    @Override
    public BoundingBox bounds () {
        return new BoundingBox(0, 0, 1, 1);
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        g.setColor(Color.RED);
        g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
