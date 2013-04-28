package laserval.ld26.graphics;

import java.util.Arrays;
import java.util.Random;

import laserval.ld26.Reach;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blob extends Polygon {

	private final Logger logger = LoggerFactory.getLogger(Blob.class);
	
	private static final long serialVersionUID = 201304271651L;

	private Vector2f origin;
	
	private int resolution;

	private Reach reach;

	public Blob(Vector2f origin, Reach reach, int resolution) {
		super();
		this.resolution = resolution;
		this.origin = origin;
		this.reach = reach;
		for (int i = 0; i < resolution; i++) {
			float x = calculateX(i);
			float y = calculateY(i);
			this.addPoint(x, y);
		}
	}

	public void update(int deltams) {
		for (int i = 0; i < points.length/2; i++) {
			this.points[2 * i] = calculateX(i);
			this.points[2 * i + 1] = calculateY(i);
		}
	}

	private float calculateX(int index) {
		double angle = (double) index * 2.0 * Math.PI / (double) resolution;
		return (float) Math.cos(angle) * reach.getValue(angle)
				+ this.origin.getX();
	}

	private float calculateY(int index) {
		double angle = (double) index * 2.0 * Math.PI / (double) resolution;
		return (float) Math.sin(angle) * reach.getValue(angle)
				+ this.origin.getY();
	}

	public void copyShape(Shape shape) {
		this.points = shape.getPoints();
		this.resolution = this.points.length/2;
	}
}
