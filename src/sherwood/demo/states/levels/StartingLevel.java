package sherwood.demo.states.levels;

import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.Triggered;
import sherwood.demo.entities.baddies.spike.JumperSpike;
import sherwood.demo.entities.baddies.spike.Spike;
import sherwood.demo.entities.baddies.spike.bar.SpikeBar;
import sherwood.demo.entities.baddies.triggered.TriggeredSpike;
import sherwood.demo.entities.baddies.wire.Wire;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.entities.level1.Wind;
import sherwood.demo.entities.particles.Explosion;
import sherwood.demo.entities.player.PlayerMovement;
import sherwood.demo.entities.trigger.CollisionTrigger;
import sherwood.demo.physics.BoundingBox;
import sherwood.demo.physics.Direction;
import sherwood.demo.physics.Vector;
import sherwood.demo.structures.levels.HardViewportLevel;
import sherwood.demo.structures.levels.Level;
import sherwood.demo.structures.levels.LevelState;
import sherwood.demo.structures.levels.event.Event;
import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.MixedKeyboardInput;

import java.awt.Graphics2D;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class StartingLevel extends LevelState {

    private Level level;

    private Set<Triggered> jumpSpikes;

    public StartingLevel () {
        jumpSpikes = new HashSet<>();
        reset();
    }

    @Override
    public void init () {
        GameScreen.get().requestKeyInputMechanism(new MixedKeyboardInput(EnumSet.of(Control.LEFT, Control.RIGHT, Control.UP, Control.DOWN, Control.A)));
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm(60));
        //Sound.PATHETIQUE.play();
    }

    private void reset () {
        // starts player 2 blocks above the center of the bottom of the screen
        Vector playerStart = new Vector(32 * 2 + 8, GameScreen.HEIGHT - 32 * 2 - PlayerMovement.size.y());
        playerStart = new Vector(32 * 10 + 8, GameScreen.HEIGHT - 32 * 7 - PlayerMovement.size.y());

        level = new HardViewportLevel(4, 2, playerStart);

        for (int y=0; y<5; y++)
            for (int x=0; x<6; x++)
                level.add(new Block(new Vector(32 * 4 * x + 64, 32 + y * 4 * 32), new Vector(32, 32)), 0);

        level.add(new Spike(new BoundingBox(32 * 4, GameScreen.HEIGHT - 32 * 2, 32, 32), Direction.DOWN), 0);
        level.add(new Wire(new Vector(32*3, GameScreen.HEIGHT - 32 * 5 - 32 / 2), 32 * 3), -1);

        level.add(new Wind(new Vector(0, 0)), -8);

        level.add(new SpikeBar(new Vector(32 * 4, GameScreen.HEIGHT - 5 * 32 - 8)), -1);
        level.add(new SpikeBar(new Vector(32 * 4, GameScreen.HEIGHT - 9 * 32 + 8)), -1);

        level.add(new SpikeBar(new BoundingBox(32 * 6, GameScreen.HEIGHT - 9 * 32, 32, 32)) {
            @Override
            public void draw (Graphics2D g, Vector position) {
                Drawable.paint(g, position, "SpikeBar32");
            }
        }, -1);
        level.add(new Spike(new BoundingBox(32 * 6, GameScreen.HEIGHT - 8*32, 32, 32), Direction.DOWN), 1);


        level.add(new Spike(new BoundingBox(32 * 4, GameScreen.HEIGHT - 10*32 + 8, 32, 32), Direction.UP), 1);


        level.add(new Spike(new BoundingBox(32 * 7, GameScreen.HEIGHT - 6*32, 32, 32), Direction.RIGHT), 1);
        level.add(new Spike(new BoundingBox(32 * 9, GameScreen.HEIGHT - 6*32, 32, 32), Direction.LEFT), 1);

        Set<Triggered> trig = new HashSet<>();
        trig.add(new TriggeredSpike(new BoundingBox(32 * 2, GameScreen.HEIGHT, 32, 32), new Vector(0, -18)));
        trig.add(new TriggeredSpike(new BoundingBox(32 * 3, GameScreen.HEIGHT, 32, 32), new Vector(0, -18)));
        trig.add(new TriggeredSpike(new BoundingBox(32 * 4, GameScreen.HEIGHT, 32, 32), new Vector(0, -18)));

        trig.add(new JumperSpike(new BoundingBox(32*2, GameScreen.HEIGHT - 11 * 32, 32, 32), true));

        level.add(new CollisionTrigger(new BoundingBox(32 * 2, GameScreen.HEIGHT - 6 * 32 - 4, 32, 8), trig), 1);

        jumpSpikes.clear();
        jumpSpikes.add(new JumperSpike(new BoundingBox(32 * 6, GameScreen.HEIGHT - 32 * 3, 32, 32), true));
        for (Triggered p : jumpSpikes)
            level.add(p, -1);
        for (Triggered p : trig)
            level.add(p, -1);
    }

    @Override
    public void activate (Event event) {
        switch (event) {
            case reset:
                reset();
                break;
            case playerDeath:
                System.out.println(event);
                for (int i=0; i<1000; i++)
                    level.add(new Explosion(new Vector(level.player().bounds().x(), level.player().bounds().y()), new Vector(3, 3)), 999);
                level.remove(level.player());
                break;
            case playerJump:
                jumpSpikes.forEach(a -> a.trigger());
                break;
        }
    }

    @Override
    public void draw (Graphics2D g) {
        Drawable.paint(g, Vector.ZERO, "levels/cloud");
        Drawable.draw(g, level.entities());
    }

    @Override
    public void step (EnumSet<Control> keys) {
        level.step(keys);
    }
}
