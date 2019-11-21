import javafx.scene.image.Image;

public class PlayerOne {
	private double xPos;
	private double yPos;
	
	private double velX = 0;
	private double velY = 0;
	
	private Image playerIcon;
	
	
	public PlayerOne(double x, double y){
		
		xPos = 50;
		yPos = 50;
	//	xs
		
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
	
	
}
