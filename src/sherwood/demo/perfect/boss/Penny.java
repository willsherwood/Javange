package sherwood.demo.perfect.boss;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.perfect.boss.attacks.BallAttack;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class Penny implements Stepper, Drawable {

    private BoundingBox box;
    private double velocity = 3;
    private int first = 0;
    private int phase;

    public Penny() {
        box = new BoundingBox(-96 / 2, -96 / 2, 96, 96);
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        Drawable.paint(g, position, "boss/Penny");
    }

    @Override
    public void step (EnumSet<Control> keys) {
        if (phase > 0)
            return;
        velocity += box.position().x() < GameScreen.WIDTH / 2 ? 0.15 : -0.45;
        box = box.over(new Vector(velocity, velocity / 5));
        if (box.position().x() + box.size().x() / 2 > first) {
            Level.currentLevel().underlyingLevel().add(new BallAttack(box.position().sx(first).dy(40), new Vector(0, 4 + first / 150.), (19 - first / 33) / 19f), -1);
            first += 33;
            System.out.println("OW");
        }
        if (box.position().x() > GameScreen.WIDTH / 2 && velocity < 0)
            phase++;
    }

    @Override
    public BoundingBox bounds () {
        return box;
    }
}
