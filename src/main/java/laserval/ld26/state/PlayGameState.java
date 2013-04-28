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
import laserval.ld26.util.EntityPair;

import org.apache.commons.lang3.tuple.Pair;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
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
		entities.add(new BlobEntity(blob1origin, new Blob(blob1origin, new CircularReach(50.0f),
				100), new CircularMotion()));
		Vector2f blob2origin = new Vector2f(350.0f, 400.0f);
		entities.add(new BlobEntity(blob2origin, new Blob(blob2origin, new EllipticReach(50.f, 35.0f),
				100), new CircularMotion()));

		entities.add(new RigidEntity(
				new Rectangle(130.0f, 140.0f, 40.0f, 50.0f)));
		entities.add(new RigidEntity(
				new Rectangle(280.0f, 400.0f, 30.0f, 50.0f)));
		entities.add(new RigidEntity(
				new Rectangle(200.0f, 200.0f, 45.0f, 23.0f)));
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		for (Entity entity : entities) {
			entity.render(g);
		}
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
				left.doCollison(right);
				right.doCollison(left);
			}
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
