package laserval.ld26.state;

import java.util.HashSet;
import java.util.Set;

import laserval.ld26.Entity;
import laserval.ld26.entities.BlobEntity;
import laserval.ld26.entities.RigidEntity;
import laserval.ld26.graphics.Blob;
import laserval.ld26.motion.CircularMotion;
import laserval.ld26.reach.CircularReach;
import laserval.ld26.reach.EllipticReach;
import laserval.ld26.reach.StarReach;
import laserval.ld26.util.EntityPair;

import org.apache.commons.lang3.tuple.Pair;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayGameState extends BasicGameState {

	private final Logger logger = LoggerFactory.getLogger(PlayGameState.class);
	
	private final Set<Entity> entities = new HashSet<>();

	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		Vector2f blob1origin = new Vector2f(200.0f, 150.0f);
		entities.add(new BlobEntity("A", blob1origin, new Blob(blob1origin, new EllipticReach(50, 50),
				100), new CircularMotion(new Vector2f(0, 0), (float)Math.PI/6.0f)));
		
		Vector2f blob2origin = new Vector2f(260.0f, 250.0f);
		entities.add(new BlobEntity(
				"B",
				blob2origin,
				new Blob(blob2origin, new EllipticReach(50, 100), 100),
				new CircularMotion(new Vector2f(), -.005f)));

		Vector2f blob3origin = new Vector2f(400.0f, 400.0f);
		entities.add(new BlobEntity(
				"C",
				blob3origin,
				new Blob(blob3origin, new StarReach(10, 4, 50, 50), 100),
				new CircularMotion(new Vector2f(), .001f)));

		entities.add(new RigidEntity(
				new Rectangle(130.0f, 140.0f, 40.0f, 50.0f)));
		entities.add(new RigidEntity(
				new Rectangle(300.0f, 400.0f, 30.0f, 50.0f)));
		entities.add(new RigidEntity(
				new Rectangle(200.0f, 200.0f, 45.0f, 23.0f)));
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.setAntiAlias(true);
		g.setLineWidth(2.0f);
		for (Entity entity : entities) {
			entity.render(g);
		}
		g.resetLineWidth();
	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		logger.debug("Update call");
		for (Entity entity : entities) {
			entity.update(delta);
		}
		Set<Pair<Entity, Entity>> pairs = EntityPair.getUniquePairs(entities);
		for (Pair<Entity, Entity> pair : pairs) {
			logger.debug(pair.toString());
			Entity left = pair.getLeft();
			Entity right = pair.getRight();
			if (left.collidesWith(right)) {
				logger.debug("Intersection");
				left.doCollision(right);
				right.doCollision(left);
				left.commitCollision();
				right.commitCollision();
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
