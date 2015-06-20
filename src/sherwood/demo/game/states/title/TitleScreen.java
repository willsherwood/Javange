package sherwood.demo.game.states.title;

import sherwood.demo.game.states.RandomlyGeneratedLevel;
import sherwood.demo.game.states.levels.StartingLevel;
import sherwood.demo.game.structures.Pair;
import sherwood.gameScreen.GameScreen;
import sherwood.inputs.keyboard.control.Control;
import sherwood.inputs.keyboard.control.discrete.DiscreteKeyboardInput;
import sherwood.screenStates.NullState;
import sherwood.screenStates.ScreenState;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TitleScreen extends ScreenState {

    private static final String TITLE = "Javange Demo";
    private static Font[] fonts = {
            new Font("Garamond", Font.BOLD, 80),
            new Font("Garamond", Font.PLAIN, 40)
    };
    private int selected;
    private List<Pair<String, ScreenState>> selections;
    private int titleWidth = -1;

    public TitleScreen() {
        super();
        selections = new ArrayList<>();
        selections.add(new Pair<>("Start", new StartingLevel()));
        selections.add(new Pair<>("Hard Viewport Test", new RandomlyGeneratedLevel(true)));
        selections.add(new Pair<>("Soft Viewport Test", new RandomlyGeneratedLevel(false)));
        selections.add(new Pair<>("Controls", new NullState()));
        selections.add(new Pair<>("Options", new NullState()));
    }

    @Override
    public void draw(Graphics2D g) {
        drawTitle(g);
        drawOptions(g);
    }

    @Override
    public void step(EnumSet<Control> keys) {
        if (keys.contains(Control.DOWN))
            selected = (selected + 1) % selections.size();
        else if (keys.contains(Control.UP))
            selected = (selected + selections.size() - 1) % selections.size();
        else if (keys.contains(Control.START) || keys.contains(Control.A)) {
            GameScreen.get().requestScreenStateAndInit(selections.get(selected).b);
        }
    }

    private void drawOptions(Graphics2D g) {
        Font f = g.getFont();
        g.setFont(fonts[1]);
        for (int i = 0; i < selections.size(); i++) {
            if (selected == i)
                g.setColor(Color.YELLOW);
            else
                g.setColor(Color.LIGHT_GRAY);
            g.drawString(selections.get(i).a, GameScreen.WIDTH / 2 - g.getFontMetrics(fonts[1]).stringWidth(selections.get(i).a) / 2, GameScreen.HEIGHT - 20 - 60 * (selections.size() - i));
        }
        g.setFont(f);
    }

    private void drawTitle(Graphics2D g) {
        if (titleWidth == -1)
            titleWidth = g.getFontMetrics(fonts[0]).stringWidth(TITLE);
        Font previous = g.getFont();
        g.setFont(fonts[0]);
        g.drawString(TITLE, GameScreen.WIDTH / 2 - titleWidth / 2, 100);
        g.setFont(previous);
    }

    @Override
    public void init() {
        super.init();
        GameScreen.get().requestKeyInputMechanism(new DiscreteKeyboardInput());
    }
}