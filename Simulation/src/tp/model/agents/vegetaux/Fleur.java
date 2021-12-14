package tp.model.agents.vegetaux;

import java.awt.Point;

public class Fleur extends Vegetal {

	public Fleur(Point point) {
		super(point);

	}

	@Override
	public void produire() {
		qteNectar+=1;
	}

	@Override
	public Object clone() {
		Fleur a = new Fleur(new Point(getCoord().getX(),getCoord().getY()));
		return a;
	}
	@Override
	public int getWidth() {
		return 20;
	}

	@Override
	public int getHeight() {
		return 20;
	}
}
