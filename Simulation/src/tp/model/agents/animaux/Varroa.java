package tp.model.agents.animaux;

import tp.model.agents.animaux.Abeille;

import java.awt.Point;

import tp.model.agents.Sexe;
import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.Etat;
/**
 * 
 * @author bruno
 *
 */
public class Varroa extends Animal {
	
	public Varroa(Sexe s, Point p) {
		super(s,p);
	}
		
	@Override
	public void rencontrer(Agent a) {
		//code � trouver en section 6
		if(a instanceof Abeille) {
			Abeille candidateAuParasitage = (Abeille) a;
			this.sInstaller(candidateAuParasitage);
		}
	}

	@Override
	protected void maj() {
		// TODO Auto-generated method stub
		
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
	
	/**
	 * si le Varroa a un hébergeur, il se déplace avec l'abeille
	 */
	public void seDeplacer() {
		if(hebergeur != null)
		{
			Abeille abeilleHote = (Abeille)hebergeur;
			coord = abeilleHote.getCoord();
		}
		else
		{
			super.seDeplacer();
		}
		
		
	}

	@Override
	public Object clone() {
		Varroa a = new Varroa(getSexe(), new Point(getCoord().getX(),getCoord().getY()));
		return a;
	}

}