package sherwood.demo.game.structures.levels.event;

public enum Event {

    /**
     * called when the level should be reset (R)
     */
    reset,

    /**
     * called when the player dies
     */
    playerDeath,

    /**
     * called when the player jumps
     * can be used for spikes that move when there's a jump
     */
    playerJump
}
