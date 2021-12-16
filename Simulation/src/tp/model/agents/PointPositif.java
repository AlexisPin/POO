package tp.model.agents;
import java.awt.Point;


public class PointPositif implements Cloneable{

	
	private Point p;

	public PointPositif(Point p) {
		this.p = new Point();
		this.setX((int) p.getX());
		this.setY((int) p.getY());
	}
	

	public int getY() {
		return p.y;
	}

	public int getX() {
		return p.x;
	}


	public boolean setX(int x) {
		boolean ret=false;
		if(x>=0)
		{
			this.p.x = x;
			ret=true;
		}
		else
		{
			this.p.x = 0;
		}
		return ret;
	}
	
	public boolean setY(int y) {
		boolean ret=false;
		if(y>=0)
		{
			this.p.y = y;
			ret=true;
		}
		else
		{
			this.p.y = 0;
		}
		return ret;
	}
    
	public double getRayon(PointPositif a) {
		return Math.sqrt(Math.pow(p.x - a.p.x, 2) + Math.pow(p.y - a.p.y, 2));
		
	}
	
	public String toString() {
    	return getX() + ";" + getY();
    	
    }
    
    public Object clone() {
        return new PointPositif(this.p);
    }
    
}
