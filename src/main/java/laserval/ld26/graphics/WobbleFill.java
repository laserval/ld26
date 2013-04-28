package laserval.ld26.graphics;

import java.util.Arrays;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WobbleFill implements ShapeFill {

	private final Logger logger = LoggerFactory.getLogger(WobbleFill.class);

	private Random random = new Random();

	private float[] wobble;

	private float wobblyness;
	
	private float offset;

	public WobbleFill(Shape shape, float wobblyness) {
		this.wobble = new float[shape.getPointCount()];
		this.wobblyness = wobblyness;
		this.offset = 3;
	}

	public void updateShape(Shape shape) {
		if(wobble.length != shape.getPointCount()) {
			wobble = Arrays.copyOf(wobble, shape.getPointCount());
		}
	}

	@Override
	public Color colorAt(Shape shape, float x, float y) {
		return Color.white;
	}

	@Override
	public Vector2f getOffsetAt(Shape shape, float x, float y) {
		int index = shape.indexOf(x, y);
		if (index != -1) {
			float rand = wobble[index] + (random.nextFloat() - 0.5f);
			wobble[index] = Math.abs(rand) > wobblyness ? Math.signum(rand)
					* wobblyness : rand;
			Vector2f normal = new Vector2f(shape.getNormal(index)).normalise();
			return normal.scale(wobble[index] - offset);
		} else {
			return new Vector2f();
		}
	}
}
