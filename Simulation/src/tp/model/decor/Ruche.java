package tp.model.decor;

import java.awt.Point;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Map.Entry;

import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.PointPositif;
import tp.model.agents.Sexe;
import tp.model.agents.animaux.Abeille;
import tp.model.agents.animaux.AbeilleDomestique;
import tp.model.agents.animaux.AbeilleSolitaire;
import tp.model.agents.animaux.Frelon;
import tp.model.agents.animaux.Varroa;
import tp.model.comportements.Hebergeur;

public class Ruche extends Decor implements Hebergeur{
	
	/**
	 * Liste des abeilles de la ruche 
	 */
	private HashSet<AbeilleDomestique> population;
	/**
	 * constante taille maximale de la ruche
	 */
	private static int populationMax = 1000;
	
	/**
	 * Abeille qui dorme dans la ruche ou qui rapporte du miel
	 */
	private LinkedList<AbeilleDomestique> queueAbeilles = new LinkedList<AbeilleDomestique>();
	
	/**
	 * quantit� de miel disponible dans la ruche
	 */
	private int stockMiel;
	
	public Ruche(Point p) {
		super(p);
		population = new HashSet<AbeilleDomestique>(populationMax);
	}

	@Override
	public boolean peutAccueillir(Agent a) {
		return a instanceof AbeilleDomestique && !population.contains(a) && population.size() < populationMax;
	}
	
	@Override
	public boolean accueillir(Agent a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			/* Ne pas faire �a ici: c'est � l'animal de mettre � jour ses attributs
			 * a.setHebergeur(this);
			 */
			population.add((AbeilleDomestique) a);
			ret=true;
		}
		return ret;
	}
	
	public String toString() {
		return getClass().getSimpleName() + " (" + getCoord() + ")"+ " : " + "population " + population.size() + " abeilles \n" + afficherPopulation();
		/*
		 * "\t" code une tabulation dans une chaine de caract�res
		 * "\n" un saut de ligne 
		 */
	}
	public String afficherPopulation() {
		String ret = "";
		Iterator<AbeilleDomestique> it=population.iterator();
		while(it.hasNext()) {ret+="\t*"+it.next()+"\n";}
		return ret;
	}
	
	/**
	 * Supprimer une abeille
	 */
	@Override
	public void supprimer(Animal a) {

		if(((Abeille) a).getDictionnaireAbeillesParasites().get(a) != null)
		{
			((Abeille) a).getDictionnaireAbeillesParasites().get(a).setHebergeur(null);
		}

		population.remove(a);
	}

	public int getStockMiel() {
		return stockMiel;
	}

	public void setStockMiel(int stockMiel) {
		this.stockMiel = stockMiel;
	}
	
	public void ajoutAbeillePresente(AbeilleDomestique a) {
		queueAbeilles.push(a);
	}
	
	
	public boolean abeilleDansLaRuche(AbeilleDomestique a) {
		boolean ret = false;
		if(queueAbeilles.contains(a))
		{
			ret = true;
		}
		return ret;
	}

}
