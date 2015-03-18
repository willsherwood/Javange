package sherwood.demo.structures.levels;

import sherwood.demo.entities.Entity;
import sherwood.demo.states.DepthEntity;
import sherwood.inputs.keyboard.control.Control;

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
}
