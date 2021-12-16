package tp.model.agents.vegetaux;

import java.awt.Point;
import java.util.HashSet;

import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.PointPositif;
import tp.model.agents.animaux.AbeilleDomestique;
import tp.model.agents.animaux.AbeilleSolitaire;
import tp.model.agents.animaux.Frelon;
import tp.model.agents.animaux.FrelonAsiatique;
import tp.model.comportements.Hebergeur;

public class Arbre extends Vegetal implements Hebergeur{

	/**
	 * Liste des AbeilleSolitaire et Frelons dans l'arbre
	 */
	private HashSet<Animal> population;
	
	public Arbre(Point point, double taille) {
		super(point);
		this.taille=taille;
		population = new HashSet<Animal>(getMaxHeberges());
	}

	private double taille = 1.0;
	private int nbHeberges = 0;
	

	private int getMaxHeberges() {
		return (int)(Math.pow(2,taille));
	}
	
	private double getTaille() {
		return this.taille;
	}
	@Override
	public boolean peutAccueillir(Agent a) {
		return (a instanceof AbeilleSolitaire || a instanceof Frelon)&&nbHeberges<getMaxHeberges();
	}
	
	@Override
	public boolean accueillir(Agent a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			nbHeberges++;
			population.add((Animal)a);
			ret=true;
		}
		return ret;
	}
	
	@Override
	public void produire() {
		qteNectar += Math.pow(2, taille);		
	}


	@Override
	public Object clone() {
		Arbre a = new Arbre(new Point(getCoord().getX(),getCoord().getY()),getTaille());
		return a;
	}

	@Override
	public void supprimer(Animal a) {
		population.remove(a);
	}
	
	@Override
	public int getWidth() {
		return 60;
	}

	@Override
	public int getHeight() {
		return 60;
	}
	
}