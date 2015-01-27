package sherwood.inputs.keyboard.control;

public enum Control {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    A,
    B,
    X,
    Y,
    LTRIG,
    RTRIG,
    START,
    SELECT;

    public static int getCondensed (Control c) {
        for (int i = 0; i < Control.values().length; i++)
            if (c == Control.values()[i])
                return i;
        return -1;
    }
}
