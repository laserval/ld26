package laserval.ld26.motion;

import laserval.ld26.Motion;

import org.newdawn.slick.geom.Vector2f;

public class CircularMotion implements Motion {

	@Override
	public Vector2f move(Vector2f velocity) {
		return velocity.add(Math.PI/6.0);
	}

}
