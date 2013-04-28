package laserval.ld26.entities;

import laserval.ld26.Entity;
import laserval.ld26.graphics.CyanFill;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;

public class RigidEntity implements Entity {

	private Shape shape;
	
	private ShapeFill fill = new CyanFill();
	
	public RigidEntity(Shape shape) {
		this.shape = shape;
	}
	
	@Override
	public void update(int deltams) {
		
	}

	@Override
	public void render(Graphics g) {
		g.draw(shape, fill);
	}

	@Override
	public Shape getShape() {
		return shape;
	}

	@Override
	public boolean collidesWith(Entity other) {
		return shape.intersects(other.getShape());
	}
	
	@Override
	public void doCollison(Entity other) {
		// no op
	}
}
