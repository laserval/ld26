package laserval.ld26.motion;

import laserval.ld26.Motion;

import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class CircularMotion implements Motion {

	private Vector2f velocity;

	private Transform transform;
	
	private float angle;

	public CircularMotion(Vector2f velocity, float angle) {
		this.angle = angle;
		this.velocity = velocity;
	}

	@Override
	public Vector2f move(Vector2f position, int deltams) {
		float delta = (float)deltams * MS_TO_SEC;
		transform = Transform.createRotateTransform(angle * delta);
		Vector2f v = transform.transform(velocity);
		velocity.set(v);
		return v.scale(delta).add(position);
	}

	@Override
	public Transform getTransform() {
		return transform;
	}
}
