
public class Collision {
	
	private double x1;
	private double y1;
	private double first_height;
	private double first_width;
	
	
	private double x2;
	private double y2;
	private double second_height;
	private double second_width;
	
	public Collision(double x1, double y1, double first_height, double first_width,
			double x2, double y2, double second_height, double second_width) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.first_height = first_height;
		this.first_width = first_width;
		this.second_height = second_height;
		this.second_width = second_width;
		
	}
	
	
	public boolean isCollision() {
		if (y2<250) {
		 // Checks if the right side of the play2er hits an obstacle
		 if ((x1 < 0) || (x1 + second_width) > 1250|| (x1) < (x2 + second_width) && (x1 + first_width) > x2
				 && (y1) < (y2+second_height) && (y1 + first_height) > y2) {
			 return true;
		 }
		}
		return false;
	}
	
	

}
