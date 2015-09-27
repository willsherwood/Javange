package sherwood.demo.perfect.boss;

import sherwood.demo.game.entities.Drawable;
import sherwood.demo.game.entities.Triggered;
import sherwood.demo.game.entities.baddies.spike.JumperSpike;
import sherwood.demo.game.entities.particles.Explosion;
import sherwood.demo.game.entities.player.PlayerMovement;
import sherwood.demo.game.physics.BoundingBox;
import sherwood.demo.game.physics.Vector;
import sherwood.demo.game.structures.levels.HardViewportLevel;
import sherwood.demo.game.structures.levels.Level;
import sherwood.demo.game.structures.levels.LevelState;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.demo.perfect.boss.attacks.ScreenFlash;
import sherwood.gameScreen.FPSUpdateAlgorithm;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.KeyboardInput;
import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class Stage extends LevelState {

    private Level level;
    private List<Triggered> spikeTriggers;
    private Container container;
    private Optional<ScreenFlash> playerDeath;

    public Stage () {
        container = new Container();
        go();
    }

    private void go () {
        spikeTriggers = new ArrayList<>();
        playerDeath = Optional.empty();
        level = new HardViewportLevel(1, 1, new Vector(30, GameScreen.HEIGHT - 128 - PlayerMovement.size.yc()));
        for (int i = 9; i < GameScreen.WIDTH; i += 94) {
            level.add(new BossBlock(new Vector(i, GameScreen.HEIGHT - 128), new Vector(32, 32)), 0);
            JumperSpike k = new JumperSpike(new BoundingBox(new Vector(i, GameScreen.HEIGHT - 128), new Vector(32, 32)), false);
            level.add(k, 1);
            spikeTriggers.add(k);
        }
        container.penny = new Penny();
    }

    @Override
    public void step (EnumSet<Control> keys) {
        super.step(keys);
        level.step(keys);
        container.penny.step(keys);
    }

    @Override
    public void activate (Event event) {
        if (event == Event.reset)
            go();
        if (event == Event.playerDeath) {
            level.add(new Explosion(level.player().bounds().position(), new Vector(30, 30)), -5);
            playerDeath = Optional.of(new ScreenFlash());
        }
    }

    @Override
    public void activate (Event event, int data) {
        if (event == Event.playerJump) {
            if (data < spikeTriggers.size())
                spikeTriggers.get(data).trigger();
        }
    }

    @Override
    public Level underlyingLevel () {
        return level;
    }

    @Override
    public void draw (Graphics2D g) {
        Drawable.draw(g, level.entities());
        container.penny.draw(g, container.penny.bounds().position());
        if (playerDeath.isPresent()) {
            playerDeath.get().draw(g, Vector.ZERO);
            playerDeath = Optional.empty();
        }
    }

    @Override
    public void init () {
        super.init();
        GameScreen.get().requestUpdateAlgorithm(new SkipAlgo(container, 300));
    }

    private static final class Container {
        public Penny penny;
    }

    private static final class SkipAlgo extends FPSUpdateAlgorithm {

        private final Container penny;
        private final int phase;

        public SkipAlgo (Container container, int phase) {
            super(50);
            this.penny = container;
            this.phase = phase;
        }

        @Override
        public void update (ScreenState screenState, Graphics2D graphics, GameScreen gameScreen, KeyboardInput input) {
            super.update(screenState, graphics, gameScreen, input);
            while (penny.penny.phase() < phase) {
                screenState.step(input.keys());
                graphics.drawString("phase: " + penny.penny.phase(), 100, 100);
            }
        }
    }
}
