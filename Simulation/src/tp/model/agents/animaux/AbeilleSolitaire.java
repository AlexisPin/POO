package tp.model.agents.animaux;

import java.awt.Point;

import tp.model.decor.Ruche;
import tp.model.agents.Agent;
import tp.model.agents.Etat;
import tp.model.agents.Sexe;
import tp.model.agents.vegetaux.Arbre;

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

	@Override
	protected void maj() {
		
	}
	
	@Override
	protected void seNourrir() {
		if(getNiveauSante() != Etat.Mourant)
		{
			setQteNourriture(1);
			ameliorerEtat();
		}
		if(getQteNourriture() == 0) 
		{
			setNiveauSante(Etat.Mourant);
		}
	}

}
