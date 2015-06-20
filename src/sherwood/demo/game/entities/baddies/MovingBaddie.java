package sherwood.demo.game.entities.baddies;

import sherwood.demo.game.entities.Collider;
import sherwood.demo.game.entities.Entity;
import sherwood.demo.game.entities.Mover;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.entities.blocks.Block;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.util.EnumSet;

public class MovingBaddie extends Baddie implements Mover, Collider, Stepper {

    private Vector velocity;

    public MovingBaddie (BoundingBox bounds, Vector velocity) {
        super(bounds);
        this.velocity = velocity;
    }


    @Override
    public Vector velocity () {
        return velocity;
    }

    @Override
    public void collide (Entity entity) {
        if (entity instanceof Block) {
            turnAround();
        }
    }

    @Override
    public void step (EnumSet<Control> keys) {
        moveTo(bounds().over(velocity).position());
        if (bounds().x() <= 0 || bounds().x() + bounds().width() >= GameScreen.WIDTH ||
                bounds().y() <= 0 || bounds().y() + bounds().height() >= GameScreen.HEIGHT)
            turnAround();
    }

    private void turnAround () {
        velocity = velocity.negate();
        super.moveTo(bounds().position().over(velocity));
    }

    protected void velocity (Vector vector) {
        this.velocity = vector;
    }
}
