package sherwood.demo.game.entities.player;

import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.event.Event;

public class Jumper {

    private static final double first_jump = -8.5;
    private static final double second_jump = -7;

    private int jump = 0;
    private boolean jumping;
    private PlayerMovement pm;

    public Jumper (PlayerMovement pm) {
        this.pm = pm;
    }

    /**
     * called when jump key is held
     */
    public void jump () {
        if (!jumping) {
            switch (jump) {
                case 0:
                    Level.currentLevel().activate(Event.playerJump);
                    pm.changeVelocity(pm.velocity().sy(first_jump));
                case 1:
                    Level.currentLevel().activate(Event.playerJump);
                    pm.changeVelocity(pm.velocity().sy(second_jump));
            }
            jump++;
        }
        jumping = true;
    }

    /**
     * called when jump key is released
     */
    public void stop () {
        if (pm.velocity().y() < 0)
            pm.changeVelocity(pm.velocity().sy(pm.velocity().y() / 2));
        jumping = false;
    }

    /**
     * called to reset the players allowed jumps
     */
    public void reset () {
        jump = 0;
    }
}
