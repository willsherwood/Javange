package sherwood.screenStates.recording;

import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.fullKeys.FullDiscreteKeyboardInput;
import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class RecordingWrapperState extends ScreenState {

    public static final int RECORDING_KEY = KeyEvent.VK_SPACE;
    public static final int PLAYBACK_KEY = KeyEvent.VK_ENTER;
    public static final int STOP_KEY = KeyEvent.VK_BACK_SPACE;
    protected Class<? extends ScreenState> screenClass;
    protected RecordingState recordingState;
    protected PlaybackState playbackState;
    protected STATUS status;

    public RecordingWrapperState (Class<? extends ScreenState> screenClass) {
        this.screenClass = screenClass;
        this.status = STATUS.WAITING;
    }

    @Override
    public void draw (Graphics2D g) {
        switch (status) {
            case RECORDING:
                recordingState.draw(g);
                break;
            case PLAYING:
                playbackState.draw(g);
                break;
            case WAITING:
                g.drawString(KeyEvent.getKeyText(RECORDING_KEY) + ": Record", 20, 20 + 20);
                g.drawString(KeyEvent.getKeyText(PLAYBACK_KEY) + ": Playback", 20, 40 + 20);
                g.drawString(KeyEvent.getKeyText(STOP_KEY) + ": Stop", 20, 60 + 20);
                break;
        }
    }

    @Override
    public void step (BitSet keys) {
        switch (status) {
            case RECORDING:
                if (keys.get(STOP_KEY)) {
                    status = STATUS.WAITING;
                } else
                    recordingState.step(keys);
                break;
            case PLAYING:
                if (keys.get(STOP_KEY)) {
                    status = STATUS.WAITING;
                } else
                    playbackState.step(keys);
                break;
            case WAITING:
                if (keys.get(RECORDING_KEY)) {
                    System.out.println(true);
                    try {
                        recordingState = new RecordingState(screenClass.newInstance());
                        recordingState.init();
                        status = STATUS.RECORDING;
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (keys.get(PLAYBACK_KEY)) {
                    try {
                        playbackState = new PlaybackState(screenClass.newInstance(), recordingState.getRecordedKeys());
                        playbackState.init();
                        status = STATUS.PLAYING;
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @Override
    public void init () {
        super.init();
        GameScreen.get().requestKeyInputMechanism(new FullDiscreteKeyboardInput());
    }

    public static enum STATUS {
        RECORDING,
        PLAYING,
        WAITING
    }
}
