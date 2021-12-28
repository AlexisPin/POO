package tp.model.agents.animaux;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import tp.model.agents.Agent;
import tp.model.agents.Animal;
import tp.model.agents.Etat;
import tp.model.agents.PointPositif;
import tp.model.agents.Sexe;
import tp.model.agents.vegetaux.Vegetal;
import tp.model.comportements.Hebergeur;
import tp.model.decor.Ruche;
import tp.model.world.Monde;
/**
 * Abeille est un hï¿½bergeur pour ses parasites (Varroa par exemple)
 * @author bruno
 *
 */
public abstract class Abeille extends Animal implements Hebergeur{
	
	/**
	 * parasite ï¿½ventuel de l'abeille
	 * si l'abeille est parasitï¿½e, passe ï¿½ true
	 */
	private boolean parasite=false;
	/**
	 * quantitï¿½ de miel transportï¿½ par l'abeille
	 */
	protected int qteMiel = 0;
	/**
	 * constante donnant la quantitï¿½ maximal de miel que l'abeille peut transporter
	 */
	private static final int qteMax = 10;
	
	private Map<Abeille,Varroa> dictionnaireAbeillesParasites = new HashMap<Abeille,Varroa>();
	
	private boolean rencontreVegetal = false;
	
	public Abeille(Sexe s, Point p) {
		super(s,p);
		dictionnaireAbeillesParasites.put(this, null);
	}

	@Override
	public void rencontrer(Agent a) {
		if(a instanceof Vegetal && qteMiel<Abeille.qteMax) {
			Vegetal v = (Vegetal)a;
			qteMiel = qteMiel + v.getPortionNectar();
			rencontreVegetal = true;
			
		}
		/* rencontre avec un prï¿½dateur */
		else if(a instanceof Frelon && getNiveauSante()!=Etat.Mourant) {
			setNiveauSante(Etat.EnDetresse);
			if (a.aFaim()) {setNiveauSante(Etat.Mourant);
			System.out.println(a + " à mangé" + this);}
		}
	}

	@Override
	public boolean peutAccueillir(Agent a) {
		/*
		 * l'abeille n'a pas de parasite et l'animal est un Varroa
		 */
		return a instanceof Varroa && !parasite;
	}
	
	@Override
	public boolean accueillir(Agent a) {
		boolean ret = false;
		if(peutAccueillir(a)) {
			dictionnaireAbeillesParasites.replace((Abeille) this, (Varroa) a);
			parasite = true;
			aggraverEtat();
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Supprimer un varroa
	 */
	@Override
	public void supprimer(Animal a) {
		for(Entry<Abeille, Varroa> entry : dictionnaireAbeillesParasites.entrySet()) 
		{
		     Abeille key =  entry.getKey();
		     Varroa value =  entry.getValue();
		     if(value == a)
		     {
		    	 dictionnaireAbeillesParasites.replace(key, null);
		    	 parasite = false;
		     }
		}
	}

	public static int getQtemax() {
		return qteMax;
	}
	
	public Map<Abeille, Varroa> getDictionnaireAbeillesParasites() {
		return dictionnaireAbeillesParasites;
	}
	
	@Override
	protected void maj() {
		setQteNourriture(getQteNourriture()-1);
	}
	
	@Override
	protected void seNourrir() {
		if(getNiveauSante() != Etat.Mourant && rencontreVegetal)
		{
			setQteNourriture(getInitQteNourriture());
			ameliorerEtat();
			rencontreVegetal = false;
		}
		if(getQteNourriture() == 0) 
		{
			setNiveauSante(Etat.Mourant);
		}
	}
	
}
	
