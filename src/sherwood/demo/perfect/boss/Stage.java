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
import sherwood.inputs.keyboard.control.Control;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class Stage extends LevelState {

    private Level level;
    private List<Triggered> spikeTriggers;
    private Penny penny;
    private Optional<ScreenFlash> playerDeath;

    public Stage () {
        go();
    }

    private void go() {
        spikeTriggers = new ArrayList<>();
        playerDeath = Optional.empty();
        level = new HardViewportLevel(1, 1, new Vector(30, GameScreen.HEIGHT - 128 - PlayerMovement.size.yc()));
        for (int i = 9; i < GameScreen.WIDTH; i += 94) {
            level.add(new BossBlock(new Vector(i, GameScreen.HEIGHT - 128), new Vector(32, 32)), 0);
            JumperSpike k = new JumperSpike(new BoundingBox(new Vector(i, GameScreen.HEIGHT - 128), new Vector(32, 32)), false);
            level.add(k, 1);
            spikeTriggers.add(k);
        }
        penny = new Penny();
    }

    @Override
    public void step (EnumSet<Control> keys) {
        super.step(keys);
        level.step(keys);
        penny.step(keys);
    }

    @Override
    public void activate (Event event) {
        if (event == Event.reset )
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
        penny.draw(g, penny.bounds().position());
        if (playerDeath.isPresent()) {
            playerDeath.get().draw(g, Vector.ZERO);
            playerDeath = Optional.empty();
        }
    }

    @Override
    public void init () {
        super.init();
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm(50));
    }
}
