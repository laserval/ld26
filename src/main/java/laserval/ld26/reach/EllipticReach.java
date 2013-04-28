package laserval.ld26.reach;

import laserval.ld26.Reach;

public class EllipticReach implements Reach {

	private float xscale;
	private float yscale;
	
	public EllipticReach(float xscale, float yscale) {
		this.xscale = xscale;
		this.yscale = yscale;
	}

	@Override
	public float getValue(double angle) {
		return xscale*yscale / (float) Math.sqrt(
				Math.pow(Math.sin(angle) * xscale, 2.0)
				+ Math.pow(Math.cos(angle) * yscale, 2.0));
	}

}
