package laserval.ld26.entities;

import laserval.ld26.Entity;
import laserval.ld26.Motion;
import laserval.ld26.graphics.Blob;
import laserval.ld26.graphics.WobbleFill;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class BlobEntity implements Entity {

	private String name;
	private Blob blob;
	private WobbleFill fill;

	private Vector2f origin;

	private Motion motion;
	
	private Shape collisionResult;

	public BlobEntity(String name, Vector2f origin, Blob shape, Motion motion) {
		this.name = name;
		this.origin = origin;
		this.blob = shape;
		this.motion = motion;
		this.fill = new WobbleFill(shape, 2.f);
	}

	@Override
	public void update(int deltams) {
		origin.set(motion.move(origin, deltams));
		blob.transform(motion.getTransform());
		blob.update(deltams);
	}

	@Override
	public void render(Graphics g) {
		g.draw(blob, fill);
		g.drawString(name, origin.x, origin.y);
	}

	@Override
	public Shape getShape() {
		return blob;
	}

	@Override
	public boolean collidesWith(Entity other) {
		Shape otherShape = other.getShape();
		float distance = this.origin.distance(new Vector2f(otherShape.getCenter())) - otherShape.getBoundingCircleRadius();
		return distance < blob.getBoundingCircleRadius() && blob.intersects(otherShape);
	}

	@Override
	public void doCollision(Entity other) {
		Shape[] subtraction = blob.subtract(other.getShape());
		Shape tmp = subtraction[0];
		for (int i = 1; i < subtraction.length; i++) {
			Shape[] cmb = tmp.union(subtraction[i]);
			tmp = cmb[0];
		}
		collisionResult = tmp;
	}

	@Override
	public void commitCollision() {
		blob.copyShape(collisionResult);
		fill.updateShape(blob);
	}
}
