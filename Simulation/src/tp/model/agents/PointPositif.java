package tp.model.agents;
import java.awt.Point;


public class PointPositif implements Cloneable{

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointPositif other = (PointPositif) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}

	private Point p;

	public PointPositif(Point p) {
		this.p = new Point();
		this.setX((int) p.getX());
		this.setY((int) p.getY());
	}
	

	public PointPositif(int i, int j) {
		this(new Point(Math.abs(i),Math.abs(j)));
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
