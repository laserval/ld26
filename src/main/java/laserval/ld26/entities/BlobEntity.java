package laserval.ld26.entities;

import laserval.ld26.Entity;
import laserval.ld26.Motion;
import laserval.ld26.graphics.Blob;
import laserval.ld26.graphics.WobbleFill;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class BlobEntity implements Entity {

	private Blob blob;
	private WobbleFill fill;

	private Vector2f origin;
	private Vector2f velocity = new Vector2f(.2f, .2f);

	private Motion motion;

	public BlobEntity(Vector2f origin, Blob shape, Motion motion) {
		this.origin = origin;
		this.blob = shape;
		this.motion = motion;
		this.fill = new WobbleFill(shape, 3.0f);
	}

	@Override
	public void update(int deltams) {
		origin.add(motion.move(velocity));
		blob.update(deltams);
	}

	@Override
	public void render(Graphics g) {
		g.draw(blob, fill);
	}

	@Override
	public Shape getShape() {
		return blob;
	}

	@Override
	public boolean collidesWith(Entity other) {
		return blob.intersects(other.getShape());
	}

	@Override
	public void doCollison(Entity other) {
		Shape[] subtraction = blob.subtract(other.getShape());
		Shape tmp = subtraction[0];
		for (int i = 1; i < subtraction.length; i++) {
			Shape[] cmb = tmp.union(subtraction[i]);
			tmp = cmb[0];
		}
		blob.copyShape(tmp);
		fill.updateShape(blob);
	}
}
