package laserval.ld26.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import laserval.ld26.Entity;

import org.apache.commons.lang3.tuple.Pair;

public class EntityPair extends Pair<Entity, Entity> {

	private static final long serialVersionUID = 201304271932L;
	
	private Entity left;
	private Entity right;
	
	public EntityPair(Entity left, Entity right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Entity setValue(Entity value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public Entity getRight() {
		// TODO Auto-generated method stub
		return right;
	}
	
	public static Set<Pair<Entity, Entity>> getUniquePairs(Set<Entity> entities) {
		List<Entity> l = new ArrayList<>(entities);
		Set<Pair<Entity, Entity>> pairs = new HashSet<>();
		int i = 0;
		while (i < l.size()) {
			Entity left = l.get(i++);
			for (int j = i; j < l.size(); j++) {
				Entity right = l.get(j);
				Pair<Entity, Entity> pair = new EntityPair(left, right);
				pairs.add(pair);
			}
		}
		return pairs;
	}
}