package laserval.ld26.reach;

import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import laserval.ld26.Reach;

public class EllipticReach implements Reach {

	
	protected float xscale;
	protected float yscale;
	
	protected double rotation = 0.f;
	
	public EllipticReach(float xscale, float yscale) {
		this.xscale = xscale;
		this.yscale = yscale;
	}

	@Override
	public float getValue(double angle) {
		return xscale*yscale / (float) Math.sqrt(
				Math.pow(Math.sin(rotation + angle) * xscale, 2.0)
				+ Math.pow(Math.cos(rotation + angle) * yscale, 2.0));
	}

	@Override
	public void rotate(Transform transform) {
		rotation = transform.transform(new Vector2f((float)rotation)).getTheta();
	}
}
