package tp.model.agents.animaux;

import java.awt.Point;
import java.util.ArrayList;

import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.Etat;
import tp.model.agents.Sexe;

public class FrelonAsiatique extends Frelon{

	public FrelonAsiatique(Sexe s, Point p) {
		super(s,p);
	}
	
	@Override
	public void rencontrer(Agent a) {
		if(a instanceof FrelonEuropeen) {
			setNiveauSante(Etat.EnDetresse);
		}
	}
	@Override
	public Object clone() {
		FrelonAsiatique a = new FrelonAsiatique(getSexe(), new Point(getCoord().getX(),getCoord().getY()));
		return a;
	}
}
