package sherwood.demo.states.graphics;

import sherwood.demo.entities.player.Player;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;

import java.awt.Color;
import java.awt.Graphics2D;

public class PlayerArtist implements Artist<Player> {

    @Override
    public void draw (Player player, Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fill(player.bounds().rect());
        g.setColor(Color.BLACK);
        if (player.direction() == Direction.RIGHT) {
            g.fill(player.bounds().resize(3, 3).over(new Vector(6, 3)).rect());
        } else {
            g.fill(player.bounds().resize(3, 3).over(new Vector(2, 3)).rect());
        }
    }
}
