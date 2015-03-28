package sherwood.demo.states.levels;

import sherwood.audio.Sound;
import sherwood.demo.entities.Drawable;
import sherwood.demo.entities.baddies.Spike;
import sherwood.demo.entities.blocks.Block;
import sherwood.demo.entities.particles.Explosion;
import sherwood.demo.entities.player.PlayerMovement;
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

public class StartingLevel extends LevelState {

    private Level level;

    public StartingLevel () {
        reset();
    }

    @Override
    public void init () {
        GameScreen.get().requestKeyInputMechanism(new MixedKeyboardInput(EnumSet.of(Control.LEFT, Control.RIGHT, Control.UP, Control.DOWN, Control.A)));
        GameScreen.get().requestUpdateAlgorithm(new FPSUpdateAlgorithm(60));
        Sound.PATHETIQUE.play();
    }

    private void reset () {
        // starts player 2 blocks above the center of the bottom of the screen
        Vector playerStart = new Vector(GameScreen.WIDTH / 2 - PlayerMovement.size.x() / 2,
                GameScreen.HEIGHT - 64 - PlayerMovement.size.y());
        level = new HardViewportLevel(4, 2, playerStart);

        for (int i=0; i<GameScreen.WIDTH / 32 * 4; i++)
            level.add(new Block(new Vector(i * 32, GameScreen.HEIGHT - 32), new Vector(32, 32)) {
                @Override
                public void draw (Graphics2D g, Vector position) {
                    Drawable.paint(g, position, "Horizontal_Block");
                }
            }, 0);

        for (int i=0; i<GameScreen.WIDTH / 32 * 4; i+= Math.random() * 5 + 3)
            level.add(new Spike(new BoundingBox(new Vector(i * 32, GameScreen.HEIGHT - 64), new Vector(32, 32)), Direction.UP) {
                @Override
                public void draw (Graphics2D g, Vector position) {
                    Drawable.paint(g, position, "Cloud_Spike");
                }
            }, 0);

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
