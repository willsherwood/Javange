package sherwood.demo.entities.player;

import sherwood.demo.physics.Direction;

import java.awt.Graphics2D;

public class PlayerSprite {

    private final Player player;
    private int index = 0;

    public PlayerSprite (Player player) {
        this.player = player;
    }

    public void step () {
        index = (index + 1) % 5;
    }

    public void draw (Graphics2D g) {
        if (player.direction() == Direction.RIGHT) {
            if (player.velocity().y() > 0) {
                // falling right

            } else if (player.velocity().y() == 0) {
                if (player.velocity().x() == 0) {
                    // idle right

                } else {
                    // walking right

                }
            } else {
                // jumping right

            }
        } else {
            if (player.velocity().y() > 0) {
                // falling right

            } else if (player.velocity().y() == 0) {
                if (player.velocity().x() == 0) {
                    // idle right

                } else {
                    // walking right

                }
            } else {
                // jumping right

            }
        }
    }
}
