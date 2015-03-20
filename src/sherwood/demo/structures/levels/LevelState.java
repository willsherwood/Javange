package sherwood.demo.structures.levels;

import sherwood.demo.structures.levels.event.Event;
import sherwood.screenStates.ScreenState;

public abstract class LevelState extends ScreenState {

    /**
     * execute the event
     */
    public abstract void activate(Event event);
}
