package tp.model.agents.animaux;

import java.awt.Point;
import java.util.ArrayList;

import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.Sexe;
import tp.model.agents.vegetaux.Arbre;

public class FrelonEuropeen extends Frelon {
	
	public FrelonEuropeen(Sexe s, Point p) {
		super(s,p);
		proies.add(FrelonEuropeen.class);
	}

	public ArrayList<Class<? extends Animal>> getProies()
	{
		return proies;
	}
	@Override
	public Object clone() {
		FrelonEuropeen a = new FrelonEuropeen(getSexe(), new Point(getCoord().getX(),getCoord().getY()));
		return a;
	}

}
