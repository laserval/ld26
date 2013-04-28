package laserval.ld26.graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class CyanFill implements ShapeFill {

	private float offset = 3;

	@Override
	public Color colorAt(Shape shape, float x, float y) {
		// TODO Auto-generated method stub
		return Color.cyan;
	}

	@Override
	public Vector2f getOffsetAt(Shape shape, float x, float y) {
		int index = shape.indexOf(x, y);
		return new Vector2f(shape.getNormal(index)).normalise().scale(offset).negate();
	}

}
