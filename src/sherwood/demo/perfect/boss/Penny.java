package sherwood.demo.perfect.boss;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.demo.perfect.boss.attacks.BallAttack;
import sherwood.demo.perfect.boss.attacks.CosineFruit;
import sherwood.demo.perfect.boss.attacks.Fruit;
import sherwood.demo.perfect.boss.attacks.LineAttack;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.util.EnumSet;

public class Penny implements Stepper, Drawable {

    private BoundingBox box;
    private double velocity = 3;
    private int first = 0;
    private int phase;
    private LineAttack left, right;
    private double lv;

    public Penny () {
        box = new BoundingBox(-96 / 2, -96 / 2, 96, 96);
    }

    @Override
    public void draw (Graphics2D g, Vector position) {
        Drawable.paint(g, position, "boss/Penny");
    }

    @Override
    public void step (EnumSet<Control> keys) {
//        if (phase == 0)
//            GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm(50));
        if (phase == 1) {
            Fruit.number = 0;
            phase = 2;
            left = new LineAttack(box.position().over(box.size().times(.5)), new Vector(300, GameScreen.HEIGHT - 128 - 96), false);
            right = new LineAttack(box.position().over(box.size().times(.5)), new Vector(417, GameScreen.HEIGHT - 128 + 16), true);
            Level.currentLevel().underlyingLevel().add(left, 1010101);
            Level.currentLevel().underlyingLevel().add(right, 1010101);
            return;
        }
        if (phase > 1 && phase < 100) {
            phase++;
            return;
        }
        if (phase == 100 || phase == 200) {
            Level.currentLevel().activate(Event.playerJump, 2);
            Level.currentLevel().activate(Event.playerJump, 3);
            Level.currentLevel().activate(Event.playerJump, 4);
        }
        if (phase >= 100 && phase <= 155) {
            if (phase % 4 == 0)
                Level.currentLevel().underlyingLevel().add(new Fruit(box.position(), true), Fruit.time++);
            velocity += box.position().x() > GameScreen.WIDTH / 2 + 20 ? -0.15 : 0.45;
            box = box.over(new Vector(velocity, -velocity * 1.25));
            phase++;
            if (box.position().x() > GameScreen.WIDTH / 2 + 20 && velocity > 0) {
                velocity = 0;
                return;
            }
            return;
        }
        if (phase == 155 + 60) {
            Level.currentLevel().underlyingLevel().add(new Fruit(new Vector(0, GameScreen.HEIGHT - 205), new Vector(200, 200), new Vector(8, 0)), 99);
            //GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm(15));
        }
        if (phase >= 155 && phase < 300) {
            if ((phase - 155) % 30 == 0) {
                Level.currentLevel().underlyingLevel().add(new Fruit(box.position().over(box.size().times(.5)), new Vector(-4, 4)), Fruit.time++);
            }
            phase++;
            return;
        }
        if (phase == 300) {
            Level.currentLevel().activate(Event.playerJump, 4);
            Level.currentLevel().activate(Event.playerJump, 2);
            phase++;
            return;
        }
        if (phase >= 300 && phase < 355) {
            lv += left.position().x() > GameScreen.WIDTH / 2 + 20 ? -0.15 : 0.45;
            left.move(left.position().over(new Vector(lv, -lv * 1.25)));
            right.move(right.position().over(new Vector(lv, -lv * 1.25)));

            velocity += box.position().x() > GameScreen.WIDTH / 2 - 100 ? -0.015 : 0.045;
            box = box.over(new Vector(velocity, -velocity * 1.25));

            phase++;
            if (left.position().x() > GameScreen.WIDTH / 2 + 20 && lv > 0) {
                lv = 0;
                //return;
            }
            //left.move(box.position().over(box.size().times(.5)));
            //right.move(box.position().over(box.size().times(.5)));

            if ((phase - 275) % 2 == 0) {
                Level.currentLevel().underlyingLevel().add(new Fruit(box.position().over(box.size().times(.5)), new Vector(-6, 4.5)), Fruit.time++);
                Level.currentLevel().underlyingLevel().add(new Fruit(box.position().over(box.size().times(.5)), new Vector(-5.5, 4.25)), Fruit.time++);
                Level.currentLevel().underlyingLevel().add(new Fruit(box.position().over(box.size().times(.5)), new Vector(-5, 4.125)), Fruit.time++);
                Level.currentLevel().underlyingLevel().add(new Fruit(box.position().over(box.size().times(.5)), new Vector(-4.5, 4.0625)), Fruit.time++);
                Level.currentLevel().underlyingLevel().add(new Fruit(box.position().over(box.size().times(.5)), new Vector(-4, 4)), Fruit.time++);
            }
            return;
        }
        if (phase >= 355) {
            if (phase % 11 == 0)
                Level.currentLevel().underlyingLevel().add(new CosineFruit(100), 10);
        }
        if (phase == 0) {
            velocity += box.position().x() < GameScreen.WIDTH / 2 ? 0.15 : -0.45;
            box = box.over(new Vector(velocity, velocity / 5));
            if (box.position().x() + box.size().x() / 2 > first) {
                Level.currentLevel().underlyingLevel().add(new BallAttack(box.position().sx(first).dy(40), new Vector(0, 4 + first / 150.), (19 - first / 33) / 19f), -1);
                first += 33;
            }
            if (box.position().x() > GameScreen.WIDTH / 2 && velocity < 0)
                phase++;
        }
    }

    public int phase () {
        return phase;
    }

    @Override
    public BoundingBox bounds () {
        return box;
    }
}
