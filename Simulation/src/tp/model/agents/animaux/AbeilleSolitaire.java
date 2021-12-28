package tp.model.agents.animaux;

import java.awt.Point;

import tp.model.decor.Ruche;
import tp.model.agents.Agent;
import tp.model.agents.Etat;
import tp.model.agents.Sexe;
import tp.model.agents.vegetaux.Arbre;
import tp.model.agents.vegetaux.Vegetal;

public class AbeilleSolitaire extends Abeille {

	public AbeilleSolitaire(Sexe s, Point p) {
		super(s,p);
	}
	
	@Override
	public void rencontrer(Agent a) {
		if(a instanceof Arbre) {
			Arbre candidatHebergement= (Arbre) a;
			this.sInstaller(candidatHebergement);
		}
	}
	
	@Override
	public Object clone() {
		AbeilleSolitaire a = new AbeilleSolitaire(getSexe(), new Point(getCoord().getX(),getCoord().getY()));
		return a;
	}
}
