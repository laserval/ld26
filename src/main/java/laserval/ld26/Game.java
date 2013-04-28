package laserval.ld26;

import laserval.ld26.state.PlayGameState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A game using Slick2d
 */
public class Game extends StateBasedGame {

	/** Screen width */
	private static final int WIDTH = 800;
	/** Screen height */
	private static final int HEIGHT = 600;

	public Game() {
		super("laserval ld26");
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setForceExit(false);
		app.setTargetFrameRate(60);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(new PlayGameState());
	}

}
