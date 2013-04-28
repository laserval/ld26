package laserval.ld26;

import org.newdawn.slick.geom.Transform;

public interface Reach {

	public float getValue(double angle);

	public void rotate(Transform transform);
}
