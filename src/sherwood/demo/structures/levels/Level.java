package sherwood.demo.structures.levels;

import sherwood.demo.entities.Entity;
import sherwood.demo.states.DepthEntity;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.screenStates.ScreenState;

import java.util.EnumSet;
import java.util.PriorityQueue;

public interface Level {
    /**
     * adds this entity to this level at the specified drawing depth
     */
    void add(Entity entity, int depth);

    /**
     * returns all entities visible to the player
     */
    PriorityQueue<DepthEntity> entities();

    /**
     * runs the step method of every stepper
     */
    void step(EnumSet<Control> keys);

    static LevelState currentLevel() {
        ScreenState s = GameScreen.get().getScreenState();
        if (s instanceof LevelState)
            return (LevelState) s;
        throw new RuntimeException("The current state is not a level state.");
    }
}
