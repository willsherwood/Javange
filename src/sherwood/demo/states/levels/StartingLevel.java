package sherwood.demo.states.levels;

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
        //Sound.PATHETIQUE.play();
    }

    private void reset () {
        // starts player 2 blocks above the center of the bottom of the screen
        Vector playerStart = new Vector(32 * 2 + 8, GameScreen.HEIGHT - 32 * 2 - PlayerMovement.size.y());
        level = new HardViewportLevel(4, 2, playerStart);

        for (int y=0; y<5; y++)
            for (int x=0; x<6; x++)
                level.add(new Block(new Vector(32 * 4 * x + 64, 32 + y * 4 * 32), new Vector(32, 32)), 0);

        level.add(new Spike(new BoundingBox(32 * 4, GameScreen.HEIGHT - 32 * 2, 32, 32), Direction.DOWN), 0);

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
