package laserval.ld26.graphics;

import java.util.Arrays;

import laserval.ld26.Reach;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Blob extends Polygon {

	private final Logger logger = LoggerFactory.getLogger(Blob.class);
	
	private static final long serialVersionUID = 201304271651L;

	private Vector2f origin;
	
	private int resolution;

	private Reach reach;
	
	private float[] prevReach;
	
	private float growth = 10.0f;

	public Blob(Vector2f origin, Reach reach, int resolution) {
		super();
		this.resolution = resolution;
		this.origin = origin;
		this.reach = reach;
		this.prevReach = new float[resolution/10];
		for (int i = 0; i < resolution; i++) {
			double angle = (double) i * 2.0 * Math.PI / (double) resolution;
			//prevReach[prevReachIndex(i)] = reach.getValue(angle)/2.f;
			float x = calculateX(angle, i);
			float y = calculateY(angle, i);
			this.addPoint(x, y);
		}
	}

	public void update(int deltams) {
		for (int i = 0; i < resolution; i++) {
			double angle = (double) i * 2.0 * Math.PI / (double) resolution;
			/*
			int prevReachIndex = prevReachIndex(i);
			prevReach[prevReachIndex] = reach.getValue(angle) > prevReach[prevReachIndex] ?
					prevReach[prevReachIndex] + growth*(float)deltams/1000.f : reach.getValue(angle);
					*/
			this.points[2 * i] = calculateX(angle, i);
			this.points[2 * i + 1] = calculateY(angle, i);
		}
	}

	private float calculateX(double angle, int index) {
		return (float) Math.cos(angle) * reach.getValue(angle) + this.origin.getX();
	}

	private float calculateY(double angle, int index) {
		return (float) Math.sin(angle) * reach.getValue(angle) + this.origin.getY();
	}
	
	private int prevReachIndex(int index) {
		float newIndex = (float)index/(float)resolution;
		return (int)Math.floor(newIndex*(float)prevReach.length);
	}
	
	private int invPrevReachIndex(int previndex) {
		float newIndex = (float)previndex/(float)prevReach.length;
		return (int)Math.floor(newIndex*(float)resolution);
	}

	public void copyShape(Shape shape) {
		this.points = shape.getPoints();
		this.resolution = getPointCount();
		/*
		for (int i = 0; i < prevReach.length; i++) {
			prevReach[i] = new Vector2f(getPoint(invPrevReachIndex(i))).distance(origin);
		}*/
	}
	
	@Override
	public Shape transform(Transform transform) {
		reach.rotate(transform);
		return super.transform(transform);
	}
}
