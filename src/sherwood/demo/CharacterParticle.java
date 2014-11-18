package sherwood.demo;

import java.awt.Color;
import java.awt.Graphics2D;

import sherwood.screenStates.objective.Drawable;

/**
 * 
 */
public class CharacterParticle implements Drawable {

	public static final int FADE_TIME = 10;

	private String character;
	private int life;
	private int x, y;
	
	public CharacterParticle(String string, int x, int y) {
		this.character = string;
		life = 254;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean draw(Graphics2D g) {
		if (life <= 0)
			return false;
		Color old = g.getColor();
		Color currentColor = new Color(240, 180, 200, life);
		g.setColor(currentColor);
		g.fillOval(x, y, 32, 32);
		g.setColor(Color.WHITE);
		g.drawString(character, x+16, y+24);
		life -= 14;
		return true;
	}

}
