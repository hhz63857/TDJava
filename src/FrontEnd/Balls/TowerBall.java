package FrontEnd.Balls;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import FrontEnd.GameInfo;
import Helpers.Config;
import Helpers.ImageHelper;
import Helpers.MapData;
import Helpers.TestHelper;

public abstract class TowerBall extends Ball {

	protected int xSlotNum;
	protected int ySlotNum;
	protected int size;
	protected int scope;
	protected int attack;
	static int mapID;

	public TowerBall(int x, int y, int size) {
		this(x / Config.slotWidth, y / Config.slotHeight, size, null);
	}

	// public TowerBall(int xSlotNum, int ySlotNum, int size) {
	// this(xSlotNum, ySlotNum, size, "");
	// }

	public TowerBall(int xSlotNum, int ySlotNum, int size, String imagePath) {
		super(xSlotNum * Config.slotWidth, ySlotNum * Config.slotHeight,
				Config.slotWidth * size, Config.slotHeight * size, imagePath);
		this.xSlotNum = xSlotNum;
		this.ySlotNum = ySlotNum;
		this.setMapID(mapID);
		drawTower();
	}

	public void drawTower() {
		GameInfo.currentMap[ySlotNum][xSlotNum] = this.getMapID();
	}

	public BufferedImage getImage() {
		if (this.getImagePath() == null)
			return null;
		if (this.image == null) {
			try {
				BufferedImage originalImage = ImageIO.read(new File(this
						.getImagePath()));
				this.image = ImageHelper.resizeImage(40, 40, originalImage,
						originalImage.getType());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return this.image;
	}

	public boolean defend() {
		for (Ball ball : GameInfo.balls) {
			if (ball instanceof ActiveBall) {
				int ballX = ball.getX();
				int ballY = ball.getY();
				if (this.isInScope(ballX, ballY)) {
					this.attack(ball);
				}
			}
		}
		return false;
	}

	public boolean attack(Ball ball) {
		TestHelper.print("attacking " + ball.getClass().getName() + "at "+ ball.getX() + ball.getY());
		((ActiveBall)ball).setHealth(((ActiveBall)ball).getHealth() - this.getAttack());
		return true;
	}

	public boolean isInScope(int ballX, int ballY) {
		int scope = this.getScope();
		int x = this.getX();
		int y = this.getY();
		return (ballX < x + Config.slotWidth * scope
				&& ballX > x - Config.slotWidth
				&& ballY < y + Config.slotHeight && ballY > y
				- Config.slotHeight);
	}
	
	public Object getShape() {
		return new Ellipse2D.Double(getX(), getY(), 1, 1);
	}

	public int getMapID() {
		return mapID;
	}

	public void setMapID(int mapID) {
		this.mapID = mapID;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

}