package FrontEnd.Balls;

import FrontEnd.GameInfo;
import Helpers.Config;

public class SoliderBall extends DragonBall {

	int attack = 10;
	int scope = 1000;
	public SoliderBall(int x, int y, int XIZE, int YSIZE, int stepLength,
			String imagePath) {
		super(x, y, XIZE, YSIZE, stepLength, imagePath);
	}

	public SoliderBall(int x, int y) {
		this(x, y, 10, 10, 13, Config.SoliderBallImagePath);
	}

	public boolean hunt() {
		Ball target = null;
		for (int i = 0; i < GameInfo.balls.size(); i++) {
			Ball ball = GameInfo.balls.get(i);
			if (ball instanceof DragonBall && !(ball instanceof HeroBall)
					&& !(ball instanceof SoliderBall)) {
				if (this.isInScope(ball.getX(), ball.getY())) {
					target = ball;
				}
			}
		}
		if (target == null)
			return false;
		boolean r = this.move(target);
		if (r)
			this.attack(target);
		return true;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}


}