package laserval.ld26;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public interface Entity {

	public void update(int deltams);

	public void render(Graphics g);

	public Shape getShape();
	
	public boolean collidesWith(Entity other);
	
	public void doCollison(Entity other);
}
