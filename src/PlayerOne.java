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
		
	private boolean canJump;
	
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
	
	private Group rightGroup;
	
	
	
	public PlayerOne(double x, double y){
		
		this.xPos = x;
		this.yPos = y;
		
		this.canJump = true;
		
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
	
	
	

	public Group getRightGroup() {
		return this.rightGroup;
	}
	
	public void render(GraphicsContext g) {
		g.drawImage(this.playerPic.getImage(), xPos, yPos);
	}
	
	public double getX() {
		return this.xPos;
	}
	
	public double getY() {
		return this.yPos;
	}
	
	public void setX(double x) {
		this.xPos = x;
	}
	public void setY(double y) {
		this.yPos = y;
	}
	public Image getPlayerImg() {
		return this.playerPic.getImage();
	}
	public void SetPlayerImg(ImageView iv) {
		this.playerPic = iv;
	}
	public void incrementX() {
		this.xPos=this.xPos+7;
	}
	public void decrementX() {
		this.xPos=this.xPos-7;
	}


	public boolean isCanJump() {
		return canJump;
	}



	public void setCanJump(boolean canJump) {
		this.canJump = canJump;
	}




	public ImageView getPpR3() {
		return ppR3;
	}




	public void setPpR3(ImageView ppR3) {
		this.ppR3 = ppR3;
	}




	public ImageView getPpR2() {
		return ppR2;
	}




	public void setPpR2(ImageView ppR2) {
		this.ppR2 = ppR2;
	}




	public ImageView getPpR4() {
		return ppR4;
	}




	public void setPpR4(ImageView ppR4) {
		this.ppR4 = ppR4;
	}




	public ImageView getPpR5() {
		return ppR5;
	}




	public void setPpR5(ImageView ppR5) {
		this.ppR5 = ppR5;
	}




	public ImageView getPpL1() {
		return ppL1;
	}




	public void setPpL1(ImageView ppL1) {
		this.ppL1 = ppL1;
	}




	public ImageView getPpL2() {
		return ppL2;
	}




	public void setPpL2(ImageView ppL2) {
		this.ppL2 = ppL2;
	}




	public ImageView getPpL3() {
		return ppL3;
	}




	public void setPpL3(ImageView ppL3) {
		this.ppL3 = ppL3;
	}




	public ImageView getPpL4() {
		return ppL4;
	}




	public void setPpL4(ImageView ppL4) {
		this.ppL4 = ppL4;
	}




	public ImageView getPpL5() {
		return ppL5;
	}




	public void setPpL5(ImageView ppL5) {
		this.ppL5 = ppL5;
	}
	
	public ImageView getPpR1() {
		return ppR1;
	}




	public void setPpR1(ImageView ppR1) {
		this.ppR1 = ppR1;
	}




	public double getVelX() {
		return velX;
	}




	public void setVelX(double velX) {
		this.velX = velX;
	}




	public double getVelY() {
		return velY;
	}




	public void setVelY(double velY) {
		this.velY = velY;
	}




	public int getHealth() {
		return health;
	}




	public void setHealth(int health) {
		this.health = health;
	}
	
	// add if statements for different damage
	public void lostHealth() {
		this.health = this.health - 10;
	}




	public int getLives() {
		return lives;
	}




	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void lostLife() {
		this.lives--;
	}
	
}
