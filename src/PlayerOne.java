import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerOne{
	private double xPos;
	private double yPos;
	
	private double velX = 0;
	private double velY = 0;
	
	private double speed = 3;
	private double jumpStrength = 13;
	private double weight = 1;
		
	private boolean canJump = false;
	
	private boolean canMoveRight = false;
	private boolean canMoveLeft = false;
	
	private boolean movingUp = false;
	private boolean movingDown = false;
	private boolean movingRight = false;
	private boolean movingLeft = false;
	
	private boolean inLava = false;

	// unused vars
//	private boolean animateRight;
//	private boolean animateLeft;
//	private boolean animateJump;
//	private boolean animateStill;
	
	private int health;
	private int lives;
	
	private ImageView playerPic;
	
	private ImageView ppR1;
	private ImageView ppR2;
	private ImageView ppR3;
	private ImageView ppR4;
	private ImageView ppR5;
	
	private ImageView ppL1;
	private ImageView ppL2;
	private ImageView ppL3;
	private ImageView ppL4;
	private ImageView ppL5;
	
	private int playerImgNumber = 0;
	
	private Group rightGroup;
	
	
	
	public PlayerOne(double x, double y){
		
		this.xPos = x;
		this.yPos = y;
		
		this.setHealth(100);
		this.setLives(3);
		
		this.playerPic = new ImageView("imgs/ElfR1.png");
		
		this.ppR1 = new ImageView("imgs/ElfR1.png");
		this.setPpR2(new ImageView("imgs/ElfR2.png"));
		this.setPpR3(new ImageView("imgs/ElfR3.png"));
		this.setPpR4(new ImageView("imgs/ElfR4.png"));
		this.setPpR5(new ImageView("imgs/ElfR5.png"));
		

		this.setPpL1(new ImageView("imgs/ElfL1.png"));
		this.setPpL2(new ImageView("imgs/ElfL2.png"));
		this.setPpL3(new ImageView("imgs/ElfL3.png"));
		this.setPpL4(new ImageView("imgs/ElfL4.png"));
		this.setPpL5(new ImageView("imgs/ElfL5.png"));
		
		this.rightGroup = new Group(this.ppR1);
		
		rightGroup.setTranslateX(40);
		rightGroup.setTranslateY(200);
	}
	
	public void setInLava(boolean lava) {
		inLava = lava;
	}
	
	public boolean inLava() {
		return inLava;
	}
	
	public boolean movingUp() {
		return movingUp;
	}
	
	public boolean movingDown() {
		return movingDown;
	}
	
	public boolean movingRight() {
		return movingRight;
	}
	
	public boolean movingLeft() {
		return movingLeft;
	}
	
	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}
	
	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	
	}
	
	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	
	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}
	
	/**
	 * returns group of right movement images, not used
	 * @return rightGroup
	 */
	public Group getRightGroup() {
		return this.rightGroup;
	}
	/**
	 * renders image, for sprite draft, not used
	 * @param g
	 */
	public void render(GraphicsContext g) {
		g.drawImage(this.playerPic.getImage(), xPos, yPos);
	}
	/**
	 * returns xPos
	 * @return xPos
	 */
	public double getX() {
		return this.xPos;
	}
	/**
	 * returns yPos
	 * @return yPos
	 */
	public double getY() {
		return this.yPos;
	}
	/**
	 * sets xPos
	 * @param x
	 */
	public void setX(double x) {
		this.xPos = x;
	}
	/**
	 * sets YPos
	 * @param y
	 */
	public void setY(double y) {
		this.yPos = y;
	}
	/**
	 * returns image of player's base picture
	 * @return playerPic image
	 */
	public Image getPlayerImg() {
		return this.playerPic.getImage();
	}
	/**
	 * set player base picture
	 * @param iv
	 */
	public void SetPlayerImg(ImageView iv) {
		this.playerPic = iv;
	}
	/**
	 * incrementXPos by 7
	 */
	public void incrementX() {
		this.xPos=this.xPos+7;
	}
	/**
	 * decrementXPos by 7
	 */
	public void decrementX() {
		this.xPos=this.xPos-7;
	}

	/**
	 * returns jump flag
	 * @return canJump
	 */
	public boolean isCanJump() {
		return canJump;
	}

	/**
	 * sets jump flag
	 * @param canJump
	 */
	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}

	/**
	 * gets third player img for right movement
	 * @return ppR3
	 */
	public ImageView getPpR3() {
		return ppR3;
	}

	/**
	 * sets third player img for right movement
	 * @param ppR3
	 */
	public void setPpR3(ImageView ppR3) {
		this.ppR3 = ppR3;
	}

	/**
	 * gets second player img for right movement
	 * @return ppR2
	 */
	public ImageView getPpR2() {
		return ppR2;
	}

	/**
	 * sets second player img for right movement
	 * @param ppR2
	 */
	public void setPpR2(ImageView ppR2) {
		this.ppR2 = ppR2;
	}

	/**
	 * gets fourth player img for right movement
	 * @return ppR4
	 */
	public ImageView getPpR4() {
		return ppR4;
	}

	/**
	 * sets fourth player img for right movement
	 * @param ppR4
	 */
	public void setPpR4(ImageView ppR4) {
		this.ppR4 = ppR4;
	}
	
	/**
	 * gets fifth player img for right movement
	 * @return ppR5
	 */
	public ImageView getPpR5() {
		return ppR5;
	}

	/**
	 * sets fifth player img for right movement
	 * @param ppR5
	 */
	public void setPpR5(ImageView ppR5) {
		this.ppR5 = ppR5;
	}
	
	/**
	 * gets first player img for left movement
	 * @return ppL1
	 */
	public ImageView getPpL1() {
		return ppL1;
	}

	/**
	 * sets first player img for left movement
	 * @param ppL1
	 */
	public void setPpL1(ImageView ppL1) {
		this.ppL1 = ppL1;
	}

	/**
	 * gets second player img for left movement
	 * @return ppL2
	 */
	public ImageView getPpL2() {
		return ppL2;
	}

	/**
	 * sets second player img for left movement
	 * @param ppL2
	 */
	public void setPpL2(ImageView ppL2) {
		this.ppL2 = ppL2;
	}

	/**
	 * gets third player img for left movement
	 * @return ppL3
	 */
	public ImageView getPpL3() {
		return ppL3;
	}

	/**
	 * sets third player img for left movement
	 * @param ppL3
	 */
	public void setPpL3(ImageView ppL3) {
		this.ppL3 = ppL3;
	}

	/**
	 * gets fourth player img for left movement
	 * @return ppL4
	 */
	public ImageView getPpL4() {
		return ppL4;
	}

	/**
	 * sets fourth player img for left movement
	 * @param ppL4
	 */
	public void setPpL4(ImageView ppL4) {
		this.ppL4 = ppL4;
	}

	/**
	 * gets fifth player img for left movement
	 * @return ppL5
	 */
	public ImageView getPpL5() {
		return ppL5;
	}



	/**
	 * sets fifth player img for left movement
	 * @param ppL5
	 */
	public void setPpL5(ImageView ppL5) {
		this.ppL5 = ppL5;
	}
	
	/**
	 * gets first player img for right movement
	 * @return ppR1
	 */
	public ImageView getPpR1() {
		return ppR1;
	}


	/**
	 * sets first player img for right movement
	 * @param ppR1
	 */
	public void setPpR1(ImageView ppR1) {
		this.ppR1 = ppR1;
	}

	/**
	 * returns x Velocity
	 * @return velX
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * sets X Velocity
	 * @param velX
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * returns Y velocity
	 * @return velY
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * sets velocity Y
	 * @param velY
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}

	/**
	 * 
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * sets character health
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	// add if statements for different damage
	/**
	 * called on damaged health event
	 */
	public void lostHealth() {
		this.health = this.health - 10;
	}

	/**
	 * returns lives left
	 * @return lives
	 */
	public int getLives() {
		return lives;
	}
	
	/**
	 * 
	 * @param lives
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	/**
	 * called when a life is lost
	 */
	public void lostLife() {
		this.lives--;
	}

	/**
	 * returns right movement flag
	 * @return
	 */
	public boolean isCanMoveRight() {
		return canMoveRight;
	}

	/**
	 * set right movement flag
	 * @param canMoveRight
	 */
	public void setCanMoveRight(boolean canMoveRight) {
		this.canMoveRight = canMoveRight;
	}

	/**
	 * returns jumpstrength
	 * @return
	 */
	public double getJumpStrength() {
		return jumpStrength;
	}

	/**
	 * sets jumpstrength
	 * @param jumpStrength
	 */
	public void setJumpStrength(double jumpStrength) {
		this.jumpStrength = jumpStrength;
	}

	
	/**
	 * returns character speed
	 * @return speed
	 */
	public double getSpeed() {
		return speed;
	}

	
	/**
	 * 
	 * @return returns character's weight
	 */
	public double getWeight() {
		return weight;
	}


	/**
	 * returns left movement flag
	 * @return canMoveLeft
	 */
	public boolean isCanMoveLeft() {
		return canMoveLeft;
	}

	/**
	 * set left movement flag
	 * @param canMoveLeft
	 */
	public void setCanMoveLeft(boolean canMoveLeft) {
		this.canMoveLeft = canMoveLeft;
	}



	/**
	 * returns number of image to be rendered
	 * @return playerImgNumber
	 */
	public int getPlayerImgNumber() {
		return playerImgNumber;
	}



	/**
	 * set img number
	 * @param playerImgNumber
	 */
	public void setPlayerImgNumber(int playerImgNumber) {
		this.playerImgNumber = playerImgNumber;
	}
	
}
