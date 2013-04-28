package laserval.ld26;

import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public interface Motion {

	public static final float MS_TO_SEC = 1.f / 1000.f;

	public Vector2f move(Vector2f position, int deltams);
	
	public Transform getTransform();
}
