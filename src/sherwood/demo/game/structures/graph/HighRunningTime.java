package sherwood.demo.game.structures.graph;

public @interface HighRunningTime {

    RunningTime complexity ();

    public enum RunningTime {
        LINEAR, POLYNOMIAL, EXPONENTIAL;
    }
}
