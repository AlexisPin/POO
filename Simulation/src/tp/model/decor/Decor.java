package tp.model.decor;

import java.awt.Point;
import java.io.File;

import tp.model.agents.PointPositif;
import tp.model.world.Dessinable;

public abstract class Decor implements Dessinable{
	/**
	 * coordonnées de l'élément de décor
	 */
	private PointPositif coord;

	public Decor(Point p) {
		coord = new PointPositif(p);
	}
	
	/**
	 * renvoie un clone de la position (la position de l'agent ne sera pas modifiable par l'intermédiaire de 
	 * l'objet renvoyé
	 * @return un clone de {@link #coord}
	 */
	public PointPositif getCoord() {
		return (PointPositif)coord.clone();
	}
	
	public String getImage() {
		 return "images"+File.separator+getClass().getSimpleName()+".gif";
	}
	
	@Override
	public int getWidth() {
		return 50;
	}

	@Override
	public int getHeight() {
		return 50;
	}

}
