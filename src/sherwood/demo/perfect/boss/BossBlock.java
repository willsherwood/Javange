package sherwood.demo.perfect.boss;

import sherwood.demo.game.entities.Stepper;
import sherwood.demo.game.entities.blocks.Block;
import sherwood.demo.game.physics.Vector;
import sherwood.inputs.keyboard.control.Control;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.EnumSet;

public class BossBlock extends Block implements Stepper {
    public BossBlock (Vector position, Vector size) {
        super(position, size);
    }

    @Override
    public void step (EnumSet<Control> keys) {

    }

    @Override
    public void draw (Graphics2D g, Vector position) {
//        g.setColor(Color.DARK_GRAY.darker().darker());
//        g.fillRect(position.xc() - 12, position.yc() - 12, (int) bounds().width() + 24, (int) bounds().height() + 24);
        g.setColor(Color.WHITE);
        g.fillRect(position.xc(), position.yc(), (int) bounds().width(), (int) bounds().height());
    }
}
