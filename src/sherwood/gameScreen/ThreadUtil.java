package sherwood.gameScreen;

public class ThreadUtil {

    private ThreadUtil () {

    }

    public static void sleep (long time) {
        if (time <= 0)
            return;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
