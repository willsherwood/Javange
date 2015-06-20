package sherwood.demo.game.states.transitions;

import sherwood.demo.game.structures.levels.LevelState;
import sherwood.demo.game.structures.levels.event.Event;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.EnumSet;

public class FadeInLevel extends LevelState {

    private LevelState state;
    private int frames;
    private int frame;

    public FadeInLevel (LevelState state, int frames) {
        this.state = state;
        this.frames = frames;
    }

    @Override
    public void activate (Event event) {
        state.activate(event);
    }

    @Override
    public void draw(Graphics2D g) {
        state.draw(g);
        g.setColor(new Color(0, 0, 0, 255 - (int) (255. / frames * frame)));
        g.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
    }

    @Override
    public void step(EnumSet<Control> keys) {
        if (frame == frames) {
            GameScreen.get().requestScreenState(state);
            return;
        }
        state.step(keys);
        frame++;
    }

    @Override
    public void init() {
        super.init();
        state.init();
    }
}