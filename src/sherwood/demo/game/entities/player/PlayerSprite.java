package sherwood.demo.game.entities.player;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.graphics.SpriteBox;
import sherwood.demo.game.physics.Direction;
import sherwood.demo.game.physics.Vector;

import java.awt.Graphics2D;
import java.awt.Image;

public class PlayerSprite {

    private final Player player;
    private final byte[][] idle = {{-6, -7, -8, -7}, {0, 0, 0, 1}};
    private final byte[] walk = {-9, -9, -6, -9, -9};
    private final byte[] jump = {-1, -2};
    private final byte[] fall = {-8, -9};
    private double index = 0;

    public PlayerSprite (Player player) {
        this.player = player;
    }

    public void step () {
        index += 1 / 6.;
    }

    public void draw (Graphics2D g, Vector position) {
//        g.setColor(Color.MAGENTA);
//        g.fillRect(position.xc(), position.yc(), 11, 21);
        if (player.direction() == Direction.RIGHT) {
            if (player.velocity().y() >= 1) {
                // falling right
                Drawable.paint(g, position.over(new Vector(walk[n() % 2], 0)), "player/fall/fall" + n() % 2);
            } else if (player.velocity().y() > -1) {
                if (player.velocity().x() == 0) {
                    // idle right
                    Drawable.paint(g, position.over(new Vector(idle[0][n() % 4], idle[1][n() % 4])), "player/idle/idle" + n() % 4);
                } else {
                    // walking right
                    Drawable.paint(g, position.over(new Vector(walk[n() % 5], 0)), "player/walk/walk" + n() % 5);
                }
            } else {
                // jumping right
                Drawable.paint(g, position.over(new Vector(jump[n() % 2], 0)), "player/jump/jump" + n() % 2);
            }
        } else {
            if (player.velocity().y() >= 1) {
                // falling right
                Image toDraw = SpriteBox.instance().sprite("res/img/player/fall/fall" + n() % 2 + ".png");
                position = position.over(new Vector(-fall[n() % 2] + 11, 0));
                g.drawImage(toDraw, position.xc(), position.yc(), -toDraw.getWidth(null), toDraw.getHeight(null), null);
            } else if (player.velocity().y() > -1) {
                if (player.velocity().x() == 0) {
                    // idle right
                    Image toDraw = SpriteBox.instance().sprite("res/img/player/idle/idle" + n() % 4 + ".png");
                    position = position.over(new Vector(-idle[0][n() % 4] + 11, idle[1][n() % 4]));
                    g.drawImage(toDraw, position.xc(), position.yc(), -toDraw.getWidth(null), toDraw.getHeight(null), null);

                } else {
                    // walking right
                    Image toDraw = SpriteBox.instance().sprite("res/img/player/walk/walk" + n() % 5 + ".png");
                    position = position.over(new Vector(-walk[n() % 5] + 11, 0));
                    g.drawImage(toDraw, position.xc(), position.yc(), -toDraw.getWidth(null), toDraw.getHeight(null), null);
                }
            } else {
                // jumping right
                Image toDraw = SpriteBox.instance().sprite("res/img/player/jump/jump" + n() % 2 + ".png");
                position = position.over(new Vector(-jump[n() % 2] + 11, 0));
                g.drawImage(toDraw, position.xc(), position.yc(), -toDraw.getWidth(null), toDraw.getHeight(null), null);
            }
        }
    }

    private int n () {
        return (int) index;
    }
}
