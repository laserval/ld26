package laserval.ld26.reach;

import org.newdawn.slick.geom.Transform;

import laserval.ld26.Reach;

public class CircularReach implements Reach {

	private float radius;

	public CircularReach(float radius) {
		this.radius = radius;
	}

	@Override
	public float getValue(double angle) {
		return radius;
	}

	@Override
	public void rotate(Transform transform) {
		// no op
	}
}
