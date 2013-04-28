package laserval.ld26.reach;

public class StarReach extends EllipticReach {

	private float variation;
	private float period;
	
	public StarReach(float variation, float period, float xscale, float yscale) {
		super(xscale, yscale);
		this.variation = variation;
		this.period = period;
	}

	@Override
	public float getValue(double angle) {
		return super.getValue(angle) + variation * (float) Math.sin(period * (rotation + angle));
	}

}
