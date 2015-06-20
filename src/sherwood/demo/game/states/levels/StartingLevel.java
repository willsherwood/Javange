package sherwood.demo.game.states.levels;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Triggered;
import sherwood.demo.game.entities.baddies.spike.JumperSpike;
import sherwood.demo.game.entities.baddies.spike.Spike;
import sherwood.demo.game.entities.baddies.spike.bar.SpikeBar;
import sherwood.demo.game.entities.baddies.triggered.TriggeredSpike;
import sherwood.demo.game.entities.baddies.wire.Wire;
import sherwood.demo.game.entities.baddies.wraparound.WrappingOrb;
import sherwood.demo.game.entities.blocks.Block;
import sherwood.demo.game.entities.level1.Wind;
import sherwood.demo.game.entities.particles.Explosion;
import sherwood.demo.game.entities.player.PlayerMovement;
import sherwood.demo.game.entities.switches.Switch;
import sherwood.demo.game.entities.switches.SwitchingSpike;
import sherwood.demo.game.entities.trigger.CollisionTrigger;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Direction;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.HardViewportLevel;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.LevelState;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.MixedKeyboardInput;

import java.awt.*;
import java.util.*;

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
        //playerStart = new Vector(32 * 10 + 8, GameScreen.HEIGHT - 32 * 7 - PlayerMovement.size.y());
        //playerStart = new Vector(32 * 14 + 8, 32);

        level = new HardViewportLevel(4, 2, playerStart);

        for (int y=0; y<5; y++)
            for (int x=0; x<6; x++)
                level.add(new Block(new Vector(32 * 4 * x + 64, 32 + y * 4 * 32), new Vector(32, 32)), 0);
        level.add(new Block(new Vector(32 * 9, 9 * 32), new Vector(32, 32)), 0);


        level.add(new Spike(new BoundingBox(32 * 4, GameScreen.HEIGHT - 32 * 2, 32, 32), Direction.DOWN), 0);
        level.add(new Wire(new Vector(32*3, GameScreen.HEIGHT - 32 * 5 - 32 / 2), 32 * 3), -1);
        level.add(new Wire(new Vector(32*7, GameScreen.HEIGHT - 32 * 9 - 32 / 2), 32 * 3), -1);
        level.add(new Wire(new Vector(32*3, GameScreen.HEIGHT - 32 * 13 - 32 / 2), 32 * 3), -1);
        level.add(new Wire(new Vector(32*7, GameScreen.HEIGHT - 32 * 13 - 32 / 2), 32 * 3), -1);


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
        level.add(new Spike(new BoundingBox(32 * 6, 8*32, 32, 32), Direction.UP), 1);

        level.add(new Spike(new BoundingBox(32 * 7, GameScreen.HEIGHT - 6*32, 32, 32), Direction.RIGHT), 1);

        Set<Triggered> trig = new HashSet<>();
        trig.add(new TriggeredSpike(new BoundingBox(32 * 2, GameScreen.HEIGHT, 32, 32), new Vector(0, -18)));
        trig.add(new TriggeredSpike(new BoundingBox(32 * 3, GameScreen.HEIGHT, 32, 32), new Vector(0, -18)));
        trig.add(new TriggeredSpike(new BoundingBox(32 * 4, GameScreen.HEIGHT, 32, 32), new Vector(0, -18)));
        trig.add(new JumperSpike(new BoundingBox(32*2, GameScreen.HEIGHT - 11 * 32, 32, 32), true));

        SwitchingSpike si = new SwitchingSpike(new BoundingBox(2*32, GameScreen.HEIGHT - 3 * 32, 32, 32), Direction.UP, false);
        trig.add(si);
        level.add(si, 0);

        level.add(new CollisionTrigger(new BoundingBox(32 * 2, GameScreen.HEIGHT - 6 * 32 - 4, 32, 8), trig, true), 1);

        jumpSpikes.clear();
        jumpSpikes.add(new JumperSpike(new BoundingBox(32 * 6, GameScreen.HEIGHT - 32 * 3, 32, 32), true));
        jumpSpikes.add(new JumperSpike(new BoundingBox(32 * 6, GameScreen.HEIGHT - 32 * 7, 32, 32), true));
        jumpSpikes.add(new JumperSpike(new BoundingBox(32 * 10, GameScreen.HEIGHT - 32 * 2, 32, 32), false));

        for (Triggered p : jumpSpikes)
            level.add(p, -1);
        for (Triggered p : trig)
            level.add(p, -1);

        //SwitchingSpike s = new SwitchingSpike(new BoundingBox(32 * 11, GameScreen.HEIGHT - 32 * 6, 32, 32), Direction.RIGHT, true);
        //level.add(s, 0);

        level.add(new Spike(new BoundingBox(32 * 11, GameScreen.HEIGHT - 32 * 6, 32, 32), Direction.RIGHT), 0);

        SwitchingSpike s1 = new SwitchingSpike(new BoundingBox(32 * 8, GameScreen.HEIGHT - 32 * 6, 64, 32), Direction.LEFT, true);
        level.add(s1, 0);
        Triggered s2 = new SwitchingSpike(new BoundingBox(32 * 12, 5 * 32, 64, 32), Direction.LEFT, false);
        level.add(s2, 0);

        level.add(new Switch(new BoundingBox(32 * 10, GameScreen.HEIGHT - 8 * 32, 24, 24), Arrays.asList(s1, s2)), 3);

        level.add(new Spike(new BoundingBox(10*32, GameScreen.HEIGHT-7*32, 32, 32), Direction.UP), 1);

        level.add(new Spike(new BoundingBox(32 * 11, 5 * 32, 32, 32), Direction.RIGHT), 1);

        for (int i=0; i<GameScreen.HEIGHT / 25; i++)
            level.add(new WrappingOrb(new BoundingBox(32*14, i * 25, 6, 6), Vector.ZERO), 100);
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
                jumpSpikes.forEach(Triggered::trigger);
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
